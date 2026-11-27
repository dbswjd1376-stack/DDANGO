
package com.yj.secondproject.book;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ai/mood")
public class OllamaC extends HttpServlet {

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        OllalaDAO ollalaDAO = new OllalaDAO();
        String result = ollalaDAO.extract();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(result);
    }
}
