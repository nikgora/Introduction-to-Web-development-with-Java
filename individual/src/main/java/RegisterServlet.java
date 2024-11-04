// RegisterServlet.java

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримання параметрів з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Створення об'єкту User
        User user = new User("test","1","test");
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // Збереження користувача (логіка збереження в базу даних)
        // UserDAO.save(user);

        // Перенаправлення на сторінку авторизації
        response.sendRedirect("login.jsp");
    }
}
