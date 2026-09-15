package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class racerslist extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public racerslist() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String team = request.getParameter("team");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Список гонщиков</title></head>");
            out.println("<body>");
            String season = request.getParameter("season");
            if (season == null || season.trim().isEmpty()) {
                season = "не указан";
            }
            out.println("<h1>Гонщики (Сезон: " + season + ")</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td><b>Гонщик</b></td><td><b>Команда</b></td><td><b>Очки в сезоне</b></td></tr>");
            out.println("<tr><td>Иван Петров</td><td>Red Bull</td><td>400</td></tr>");
            out.println("<tr><td>Бульдог</td><td>Ferrari</td><td>300</td></tr>");
            out.println("<tr><td>Хотдог</td><td>McLaren</td><td>280</td></tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}