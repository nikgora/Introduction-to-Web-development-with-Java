import java.io.*;
import java.util.Enumeration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "HHEEEEHEHE HELLO World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();

        session.setMaxInactiveInterval(5 * 60);

        String user = (String) session.getAttribute("userprac");
        if (user == null) {
            user = request.getParameter("name");
            if (user != null) {
                session.setAttribute("userprac", user);
            } else {
                user = "Guest";
            }
        }

        Cookie[] cookies = request.getCookies();
        String visitCount = "0";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    visitCount = cookie.getValue();
                }
            }
        }

        int count = Integer.parseInt(visitCount) + 1;
        Cookie visitCookie = new Cookie("visitCount", Integer.toString(count));
        visitCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(visitCookie);

        // Hello
//        String name = request.getParameter("name"); PRAC 2
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();

        out.println("<html><body>");
        out.println("<h1>Hello, " + user + "!</h1>");
        out.println("<p> Visit count: "+visitCookie.getValue() +"</p>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p>Session Creation Time: " + new java.util.Date(session.getCreationTime()) + "</p>");
        out.println("<p>Last Accessed Time: " + new java.util.Date(session.getLastAccessedTime()) + "</p>");
        out.println("<p>Max Inactive Interval (seconds): " + session.getMaxInactiveInterval() + "</p>");


        out.println("<h2>Servlet Context Information:</h2>");

        Enumeration<String> initParams = context.getInitParameterNames();
        if (initParams.hasMoreElements()) {
            out.println("<p>Init Parameters:</p><ul>");
            while (initParams.hasMoreElements()) {
                String paramName = initParams.nextElement();
                String paramValue = context.getInitParameter(paramName);
                out.println("<li>" + paramName + " = " + paramValue + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No init parameters found.</p>");
        }

        Enumeration<String> attrNames = context.getAttributeNames();
        if (attrNames.hasMoreElements()) {
            out.println("<p>Context Attributes:</p><ul>");
            while (attrNames.hasMoreElements()) {
                String attrName = attrNames.nextElement();
                Object attrValue = context.getAttribute(attrName);
                out.println("<li>" + attrName + " = " + attrValue + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No context attributes found.</p>");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}