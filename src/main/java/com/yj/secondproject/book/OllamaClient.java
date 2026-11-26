package com.yj.secondproject.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OllamaClient {
    private static final String URL = "http://192.168.1.146:11434/api/chat";
    private static final String MODEL = "qwen2.5:7b";
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5)).build();

    public String chat(String prompt) throws Exception {
        JsonObject msg = new JsonObject();
        msg.addProperty("role", "user");
        msg.addProperty("content", prompt);

        JsonArray messages = new JsonArray();
        messages.add(msg);

        JsonObject options = new JsonObject();
        options.addProperty("temperature", 0.2);

        JsonObject body = new JsonObject();
        body.addProperty("model", MODEL);
        body.add("messages", messages);
        body.addProperty("stream", false);   // 한 번에 받기
        body.addProperty("format", "json");  // JSON 출력 강제
        body.add("options", options);

        HttpRequest req = HttpRequest.newBuilder(URI.create(URL))
                .timeout(Duration.ofSeconds(120))   // 로컬 모델이라 넉넉히
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() != 200) {
            throw new RuntimeException("Ollama error " + res.statusCode() + ": " + res.body());
        }
        // 응답: {"message": {"content": "{\"moods\":[...]}"}, ...}
        return JsonParser.parseString(res.body())
                .getAsJsonObject().getAsJsonObject("message")
                .get("content").getAsString();
    }
}
