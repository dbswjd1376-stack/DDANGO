package com.yj.secondproject.emotion;

import com.yj.secondproject.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static java.rmi.server.LogStream.log;

@WebServlet("/api/music/*")
public class MusicApiServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String path = request.getPathInfo();
        String keyName;
        String endpoint;

        if ("/recommend".equals(path)) {
            String tag = request.getParameter("tag");
            if (!"chill".equals(tag) && !"happy".equals(tag) && !"sad".equals(tag)) {
                response.sendError(400, "Unknown mood tag");
                return;
            }
            keyName = "LASTFM_API_KEY";
            endpoint = "https://ws.audioscrobbler.com/2.0/"
                    + "?method=tag.gettoptracks&format=json&limit=5"
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
        }else {
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











