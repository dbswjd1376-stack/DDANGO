package com.yj.secondproject.book;

// ===== Gson: 문자열(JSON)을 자바 객체로 읽을 때 쓰는 구글 라이브러리 =====
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class OllalaDAO {


    public final OllamaClient ollamaClient = new OllamaClient();

    //     Map<A, B> : "A를 넣으면 B가 나오는" 사전 같은 구조
    //     키(제목)는 "공백을 전부 뺀 형태"로 저장해요. (사용자가 띄어쓰기를 다르게 쳐도 찾으려고)
    private static final Map<String, String> BOOK_FILES = new HashMap<>();

    static {
        BOOK_FILES.put("운수좋은날", "book1.txt");
        BOOK_FILES.put("날개", "book2.txt");
        BOOK_FILES.put("도시와유령", "book3.txt");
        BOOK_FILES.put("변신", "book4.txt");
        BOOK_FILES.put("지킬박사와하이드씨", "book5.txt");
        BOOK_FILES.put("test", "test.txt");   // 테스트용: title=test 로 들어오면 test.txt 사용
    }

    // 사용자가 입력한 제목으로 파일명을 찾아주는 메서드 못 찾으면 null로 돌려줌
    public String findFileName(String title) {
        // title 파라미터를 아예 안 보냈거나(null), 빈 칸이면 -> 테스트 파일로 처리
        if (title == null || title.trim().isEmpty()) {
            return "test.txt";
        }
        // replaceAll("\\s+", "") : 공백(띄어쓰기, 탭 등)을 전부 제거
        String key = title.replaceAll("\\s+", "");
        // toLowerCase() : 영어 대소문자 구분 없애기 ("TEST" -> "test")
        return BOOK_FILES.get(key.toLowerCase());
    }

    // [2] resources 폴더의 파일을 문자열로 읽기
    //     "/" + fileName : 맨 앞 "/" 는 "resources 폴더 맨 위(루트)부터 찾아라"라는 뜻

    private String readResource(String fileName) throws Exception {
        // try (...) 안에서 연 파일은 블록이 끝나면 자동으로 닫힘(close 직접 안 해도 됨)
        try (InputStream in = OllalaDAO.class.getResourceAsStream("/" + fileName)) {

            if (in == null) {
                throw new IllegalStateException(fileName + " 파일을 찾을 수 없어요. resources 폴더에 있는지 확인하세요.");
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {
                // br.lines() : 파일의 모든 줄  /  joining("\n") : 줄바꿈으로 이어붙여 하나의 문자열로
                return br.lines().collect(Collectors.joining("\n"));
            }
        }
    }


    // [3] DB의 mood_test 테이블에서 감정 목록 가져오기
    //     LinkedHashMap : 넣은 순서를 그대로 유지하는 Map

    private Map<String, MoodDTO> loadMoods() {
        Map<String, MoodDTO> moods = new LinkedHashMap<>();
        // 끝나면 DB 연결이 자동으로 닫힘
        try (SqlSession ss = DdangoDB.connect()) {

            List<MoodDTO> list = ss.selectList("ddango.getAllMoods");
            for (MoodDTO m : list) {
                moods.put(m.getM_name_kor(), m);   // 한글 이름을 키로 저장
            }
        }
        return moods;
    }

    // [4] 공통 부분 A: 본문을 모델에 보내고, 모델이 답한 "원본 JSON 문자열"을 받아오기
    //     extract() 와 extract(fileName) 둘 다 이걸 사용 (코드 중복 방지)

    private String callModel(String text, Map<String, MoodDTO> moods) throws Exception {
        // 감정 목록을 "- 무서움 (fear)" 형태로 한 줄씩 만들어서 이어붙임
        // moods.values() : Map의 값들(MoodDTO)만 꺼내기
        // .map(m -> ...) : 각 MoodDTO를 문자열 한 줄로 변환
        String moodList = moods.values().stream()
                .map(m -> "- " + m.getM_name_kor() + " (" + m.getM_name_eng() + ")")
                .collect(Collectors.joining("\n"));

        // 모델에게 보낼 질문(프롬프트)
        // \" : 문자열 안에 큰따옴표(")를 쓰려면 앞에 \ 를 붙여야 함
        String prompt = "다음은 책 소개글입니다.\n\n" + text + "\n\n"
                + "아래 감정 목록 중에서 이 책의 분위기와 감정에 가장 어울리는 감정을 3개 고르세요.\n"
                + "반드시 목록에 있는 감정만 사용하고, 한글과 영문을 목록에 적힌 그대로 함께 답하세요.\n\n"
                + "[감정 목록]\n" + moodList + "\n\n"
                + "JSON으로만 답하세요. 형식 예시:\n"
                + "{\"moods\": [{\"kor\": \"편안해요\", \"eng\": \"relaxed\"}, "
                + "{\"kor\": \"기분이 좋아요\", \"eng\": \"good-mood\"}]}";

        // OllamaClient가 qwen 모델을 호출하고 답변 문자열을 돌려줌
        return ollamaClient.chat(prompt);
    }


    // [5] 공통 부분 B: 모델의 답(JSON 문자열)을 읽어서, "목록에 실제로 있는 감정"만 골라내기

    private List<MoodDTO> parseAndValidate(String content, Map<String, MoodDTO> moods) {

        // 영문 -> MoodDTO 사전 (한글이 틀렸을 때 영문으로 한 번 더 찾기 위함)
        Map<String, MoodDTO> byEng = new HashMap<>();
        for (MoodDTO m : moods.values()) {
            byEng.put(m.getM_name_eng(), m);
        }

        // 문자열을 JSON 구조로 변환
        JsonElement root = JsonParser.parseString(content);

        // 감정들이 들어있는 배열을 담을 변수 (일단 빈 배열로 시작)
        JsonArray picked = new JsonArray();

        if (root.isJsonArray()) {
            // 모델이 [ {...}, {...} ] 형태로 바로 답한 경우
            picked = root.getAsJsonArray();
        } else if (root.isJsonObject()) {
            // 모델이 { "moods": [ ... ] } 형태로 답한 경우
            // 객체 안의 항목들을 하나씩 보면서 "배열인 것"을 찾아 사용
            for (Map.Entry<String, JsonElement> en : root.getAsJsonObject().entrySet()) {
                if (en.getValue().isJsonArray()) {
                    picked = en.getValue().getAsJsonArray();
                    break;   // 찾았으니 반복 종료
                }
            }
        }
        // 검증을 통과한 감정만 담을 리스트
        List<MoodDTO> result = new ArrayList<>();

        for (JsonElement e : picked) {
            // { "kor": ..., "eng": ... } 형태(객체)가 아니면 건너뜀
            if (!e.isJsonObject()) continue;

            JsonObject o = e.getAsJsonObject();
            // o.has("kor") : "kor" 항목이 있는지 확인 (없는데 get 하면 에러나니까 먼저 확인)
            String kor = o.has("kor") ? o.get("kor").getAsString().trim() : "";
            String eng = o.has("eng") ? o.get("eng").getAsString().trim() : "";

            // 1단계: 한글로 DB 목록에서 찾기
            MoodDTO m = moods.get(kor);
            // 2단계: 한글로 못 찾았으면 영문으로 찾기
            if (m == null) m = byEng.get(eng);

            // 찾았고(null 아님), 이미 담은 감정이 아니면(중복 방지) 결과에 추가
            // 목록에 없는 단어(예: "미친")는 둘 다 null 이라 여기서 자동으로 버려짐
            if (m != null && !result.contains(m)) {
                result.add(m);
            }
        }

        // 3개를 초과하면 앞의 3개만 사용
        return result.size() > 3 ? result.subList(0, 3) : result;
    }   // ← parseAndValidate() 끝

    // [6-1] 테스트용: test.txt 고정, 모델의 원본 JSON 문자열을 그대로 반환
    public String extract() throws Exception {
        String text = readResource("test.txt").trim();   // trim() : 앞뒤 공백/줄바꿈 제거
        Map<String, MoodDTO> moods = loadMoods();
        String content = callModel(text, moods);
        System.out.println(content);   // 모델이 실제로 뭐라고 했는지 콘솔에 출력
        return content;
    }

    // [6-2] 실서비스용: 파일명을 받아서 분석, 검증된 감정 리스트를 반환
    //       같은 이름(extract)이지만 받는 값이 다르면 별개의 메서드로 인식됨 (오버로딩)
    public List<MoodDTO> extract(String fileName) throws Exception {
        String text = readResource(fileName).trim();

        // 본문이 너무 길면 모델이 앞부분을 잘라먹을 수 있어서 2000자까지만 사용
        if (text.length() > 5000) {
            text = text.substring(0, 5000);
        }

        Map<String, MoodDTO> moods = loadMoods();
        String content = callModel(text, moods);
        System.out.println(content);   // 확인용 출력 (필요 없으면 지워도 됨)
        return parseAndValidate(content, moods);
    }
}   // ← 클래스 끝