package com.yj.secondproject.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OllalaDAO {
    private final OllamaClient ollamaClient = new OllamaClient();

    // 같은 패키지(클래스패스)에 있는 파일 읽기
    private String readResource(String fileName) throws Exception {
        try (InputStream in = OllalaDAO.class.getResourceAsStream("/" + fileName)) {
            if (in == null) {
                throw new IllegalStateException(fileName + " 파일을 찾을 수 없어요. 같은 패키지 경로에 있는지 확인하세요.");
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        }
    }

    // moods.md -> {한글: 영문}
    private Map<String, String> loadMoods() throws Exception {
        Map<String, String> moods = new LinkedHashMap<>();
        for (String line : readResource("moods.md").split("\n")) {
            line = line.trim();
            if (line.startsWith("- ") && line.contains("|")) {
                String[] parts = line.substring(2).split("\\|");
                String eng = parts[0].trim();
                String kor = parts[1].trim();
                moods.put(kor, eng);
            }
        }
        return moods;
    }

    public List<String> extract() throws Exception {
        String text = readResource("test.txt").trim();
        Map<String, String> moods = loadMoods();

        String moodList = moods.keySet().stream()
                .map(k -> "- " + k)
                .collect(Collectors.joining("\n"));

        String prompt = "다음은 책 소개글입니다.\n\n" + text + "\n\n"
                + "아래 감정 목록 중에서 이 책의 분위기와 감정에 가장 어울리는 단어를 3개 고르세요.\n"
                + "반드시 목록에 있는 단어를 그대로 사용하고, 목록에 없는 단어는 절대 쓰지 마세요.\n\n"
                + "[감정 목록]\n" + moodList + "\n\n"
                + "JSON으로만 답하세요. 예: {\"moods\": [\"무서움\", \"비애\", \"절망\"]}";

        // 1) OllamaClient로 qwen 호출
        String content = ollamaClient.chat(prompt);

        // 2) JSON 파싱 + 목록 밖 단어/중복 제거
        JsonArray picked = JsonParser.parseString(content)
                .getAsJsonObject().getAsJsonArray("moods");

        List<String> result = new ArrayList<>();
        for (JsonElement e : picked) {
            String kor = e.getAsString();
            if (moods.containsKey(kor) && !result.contains(kor)) {
                result.add(kor);
            }
        }
        return result.size() > 3 ? result.subList(0, 3) : result;
    }

    // 테스트용
    public static void main(String[] args) throws Exception {
        List<String> result = new OllalaDAO().extract();
        for (String kor : result) {
            System.out.println(kor);
        }
    }
}
