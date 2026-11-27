package com.yj.secondproject.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

    // 클래스패스 루트(resources)에 있는 파일 읽기
    private String readResource(String fileName) throws Exception {
        try (InputStream in = OllalaDAO.class.getResourceAsStream("/" + fileName)) {
            if (in == null) {
                throw new IllegalStateException(fileName + " 파일을 찾을 수 없어요. resources 폴더에 있는지 확인하세요.");
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        }
    }

    // mood_test -> {한글: MoodDTO}
    private Map<String, MoodDTO> loadMoods() {
        Map<String, MoodDTO> moods = new LinkedHashMap<>();
        try (SqlSession ss = DdangoDB.connect()) {
            List<MoodDTO> list = ss.selectList("ddango.getAllMoods");
            for (MoodDTO m : list) {
                moods.put(m.getM_name_kor(), m);
            }
        }
        return moods;
    }

    public String extract() throws Exception {
        String text = readResource("test.txt").trim();
        Map<String, MoodDTO> moods = loadMoods();

        // 영문 -> DTO (한글이 틀렸을 때 보조 검증용)
        Map<String, MoodDTO> byEng = new HashMap<>();
        for (MoodDTO m : moods.values()) {
            byEng.put(m.getM_name_eng(), m);
        }

        // 프롬프트용 목록: "- 무서움 (fear)"
        String moodList = moods.values().stream()
                .map(m -> "- " + m.getM_name_kor() + " (" + m.getM_name_eng() + ")")
                .collect(Collectors.joining("\n"));

        String prompt = "다음은 책 소개글입니다.\n\n" + text + "\n\n"
                + "아래 감정 목록 중에서 이 책의 분위기와 감정에 가장 어울리는 감정을 3개 고르세요.\n"
                + "반드시 목록에 있는 감정만 사용하고, 한글과 영문을 목록에 적힌 그대로 함께 답하세요.\n\n"
                + "[감정 목록]\n" + moodList + "\n\n"
                + "JSON으로만 답하세요. 형식 예시:\n"
                + "{\"moods\": [{\"kor\": \"편안해요\", \"eng\": \"relaxed\"}, "
                + "{\"kor\": \"기분이 좋아요\", \"eng\": \"good-mood\"}]}";

        // 1) OllamaClient로 qwen 호출
        String content = ollamaClient.chat(prompt);
        System.out.println(content);

        // 2) 파싱: 배열로 오든, {"moods": [...]}로 오든 둘 다 처리
        JsonElement root = JsonParser.parseString(content);
        JsonArray picked = new JsonArray();
        if (root.isJsonArray()) {
            picked = root.getAsJsonArray();
        } else if (root.isJsonObject()) {
            for (Map.Entry<String, JsonElement> en : root.getAsJsonObject().entrySet()) {
                if (en.getValue().isJsonArray()) {
                    picked = en.getValue().getAsJsonArray();
                    break;
                }
            }
        }

        return content;
    }
        // 테스트용
    public static void main(String[] args) throws Exception {
//        List<MoodDTO> result = new OllalaDAO().extract();
//        for (MoodDTO kor : result) {
//            System.out.println(kor);
//        }
    }
}
