import java.io.*;
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

        // Hello
        String name = request.getParameter("name");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Hello, " + (name != null ? name : "World") + "!</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}