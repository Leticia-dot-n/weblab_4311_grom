package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
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
        String lang = request.getParameter("lang");

        if (lang == null || lang.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Ожидался параметр lang");
            return;
        }

        if (!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Параметр lang может принимать значение en или ru");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        Locale locale = "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : new Locale("ru", "RU");
        ResourceBundle res = ResourceBundle.getBundle("Racers", locale);

        String seasonParam = request.getParameter("season");
        String season = (seasonParam != null && !seasonParam.trim().isEmpty()) 
                        ? seasonParam 
                        : res.getString("default_season");

        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>" + res.getString("title") + "</title></head>");
            out.println("<body>");
            
            out.println("<h1>" + res.getString("header") + season + ")</h1>");
            
            out.println("<p>");
            out.println("<a href='racerslist?lang=ru&season=" + season + "'>Русский</a> | ");
            out.println("<a href='racerslist?lang=en&season=" + season + "'>English</a>");
            out.println("</p>");

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<td><b>" + res.getString("col.racer") + "</b></td>");
            out.println("<td><b>" + res.getString("col.team") + "</b></td>");
            out.println("<td><b>" + res.getString("col.points") + "</b></td>");
            out.println("</tr>");

            if ("en".equalsIgnoreCase(lang)) {
                out.println("<tr><td>Ivan Petrov</td><td>Red Bull</td><td>400</td></tr>");
                out.println("<tr><td>Buldog</td><td>Ferrari</td><td>300</td></tr>");
                out.println("<tr><td>Hotdog</td><td>McLaren</td><td>280</td></tr>");
            } else {
                out.println("<tr><td>Иван Петров</td><td>Red Bull</td><td>400</td></tr>");
                out.println("<tr><td>Бульдог</td><td>Ferrari</td><td>300</td></tr>");
                out.println("<tr><td>Хотдог</td><td>McLaren</td><td>280</td></tr>");
            }

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