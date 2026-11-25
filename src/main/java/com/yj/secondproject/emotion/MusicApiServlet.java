package com.yj.secondproject.emotion;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet("/api/music/*")
public class MusicApiServlet extends HttpServlet {

    // Servlet class load emotion -> last.fm tag map
    private static final Map<String, String> MOOD_TO_TAG = creatMoodToTag();
    private static Map<String, String> creatMoodToTag() {
        Map<String, String> map = new HashMap<>();

        addMoods(map, "chill", "relaxed");
        addMoods(map, "happy", "good-mood", "satisfaction", "happiness", "gratitude");
        addMoods(map, "sad", "feeling-down", "hurt-feelings", "sorrow", "tears",
                "discouragement", "sense-of-loss", "unrequited-love");
        addMoods(map, "calm", "peacefulness");
        addMoods(map, "upbeat", "joy");
        addMoods(map, "romantic", "excitement", "admiration", "shyness", "tenderness");
        addMoods(map, "fun", "cheerfulness");
        addMoods(map, "nostalgia", "nostalgia", "longing");
        addMoods(map, "melancholy", "disappointment", "emptiness", "forlornness",
                "helplessness", "lingering-attachment", "regret", "resignation");
        addMoods(map, "loneliness", "loneliness");
        addMoods(map, "dark", "despair", "feeling-threatened");
        addMoods(map, "angry", "indignation", "annoyance", "displeasure", "anger",
                "disapproval", "jealousy", "resentment");
        addMoods(map, "aggressive", "disgust", "mockery");
        addMoods(map, "energetic", "sense-of-superiority");
        addMoods(map, "anxiety", "worry", "restlessness", "concern", "hesitation",
                "anxiety", "panic");
        addMoods(map, "nervous", "tension");
        addMoods(map, "terror", "fear");
        addMoods(map, "eerie", "shivers");
        addMoods(map, "horror", "shock");
        addMoods(map, "love", "affection", "love");

        return map;
    }

    private static void addMoods(Map<String, String> map, String tag, String... moods) {
        for (String mood : moods) {
            map.put(mood, tag);
        }
    }

    private static String genreToTag(String genre) {
        switch (genre) {
            case "pop":     return "pop";
            case "jpop":    return "jpop";
            case "kpop":    return "kpop";
            case "hiphop":  return "hiphop";
            case "ballad":  return "ballad";
            default:
                return "null";
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String path = request.getPathInfo();
        String keyName;
        String endpoint;
//      tag 값 받아서 API KEY 값 받아 오기
        if ("/recommend".equals(path)) {
            // 브라우저가 보낸 세부 감정
            String mood = request.getParameter("mood");
            String genre = request.getParameter("genre");

            String tag = genre == null ? MOOD_TO_TAG.get(mood) : genreToTag(genre);
            // 서버에서 정해 둔 Last.fm 검색 태그

            if (tag == null) {
                response.sendError(400, "Unknown mood or genre");
                return;
            }
            keyName = "LASTFM_API_KEY";
            endpoint = "https://ws.audioscrobbler.com/2.0/"
                    + "?method=tag.gettoptracks&format=json&limit=10"
                    + "&tag=" + encode(tag) + "&api_key=";
        } else if ("/video".equals(path)) {
            String query = request.getParameter("q");
            if (query == null || query.trim().isEmpty() || query.length() > 300){
                response.sendError(400, "Invalid search query");
                return;
            }

            keyName = "YOUTUBE_API_KEY";
            endpoint = "https://www.googleapis.com/youtube/v3/search"
                    + "?part=snippet&type=video&videoEmbeddable=true&maxResults=1"
                    + "&q=" + encode(query.trim()) + "&key=";
        } else if ("/playlists".equals(path)) {
            String query = request.getParameter("q");

            if (query == null || query.trim().isEmpty() || query.length() > 100){
                response.sendError(400, "Invalid search query");
                return;
            }
            keyName = "YOUTUBE_API_KEY";
            endpoint = "https://www.googleapis.com/youtube/v3/search"
                    + "?part=snippet&type=playlist&maxResults=10"
                    + "&q=" + encode(query.trim()) + "&key=";
        } else {
            response.sendError(404);
            return;
        }

        String key = System.getenv(keyName);
        if (key == null || key.trim().isEmpty()) {
            log("Missing environment variable: " + keyName);
            response.sendError(500, "API key is not configured");
            return;
        }

        HttpURLConnection connection = (HttpURLConnection)
                new URL(endpoint + encode(key.trim())).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(8000);
        connection.setInstanceFollowRedirects(false);

        byte[] body;
        try {
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                log(path + " upstream HTTP status: " + status);
                response.sendError(502, "External API request failed");
                return;
            }

            try (InputStream input = connection.getInputStream();
                 ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[4096];
                int count;
                while ((count = input.read(buffer)) != -1) {
                    output.write(buffer, 0, count);
                }
                body = output.toByteArray();
            }
        } catch (IOException error) {
            log(path + " external API connection failed: " + error.getClass().getSimpleName());
            response.sendError(502, "External API connection failed");
            return;
        } finally {
            connection.disconnect();
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().write(body);
    }

    private static String encode(String value) throws IOException {
        return URLEncoder.encode(value, "UTF-8");
    }
}











