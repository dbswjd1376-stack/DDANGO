package com.yj.secondproject.book;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ai/mood")
public class OllamaC extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {



        request.setCharacterEncoding("UTF-8");
        // 응답이 "JSON 형식의 UTF-8 글자"라고 브라우저에게 알려줌
        response.setContentType("application/json;charset=UTF-8");

        // 주소창의 ?title=... 값을 꺼냄 (없으면 null)
        String title = request.getParameter("title");

        OllalaDAO dao = new OllalaDAO();

        // 제목으로 읽을 파일명 찾기 (title=test 또는 title 없음 -> test.txt)
        String fileName = dao.findFileName(title);

        // 등록되지 않은 제목이면 404 에러 응답 후 종료
        if (fileName == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 404
            response.getWriter().print("{\"error\":\"등록되지 않은 책입니다.\"}");
            return;   // 여기서 메서드 끝냄
        }

        try {
            List<MoodDTO> moods = dao.extract(fileName);

            // 결과를 [{"kor":"무서움","eng":"fear"}, ...] 형태의 JSON으로 만들기
            JsonArray arr = new JsonArray();
            for (MoodDTO m : moods) {
                JsonObject o = new JsonObject();
                o.addProperty("kor", m.getM_name_kor());   // 한글 이름
                o.addProperty("eng", m.getM_name_eng());   // 영문 이름
                arr.add(o);
            }
            // 브라우저로 JSON 문자열 전송
            response.getWriter().print(arr.toString());

        } catch (Exception e) {
            // 모델 서버가 꺼져 있거나 파일이 없는 등 오류가 났을 때
            e.printStackTrace();   // 서버 콘솔에 에러 내용 출력 (원인 파악용)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);   // 500
            response.getWriter().print("{\"error\":\"감정 분석에 실패했습니다.\"}");
        }

    }
}