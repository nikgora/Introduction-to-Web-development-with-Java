// LoginServlet.java

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримання параметрів з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Аутентифікація користувача (логіка перевірки в базі даних)
        // User user = UserDAO.authenticate(username, password);

        User user = new User(); // Тимчасовий об'єкт для прикладу
        user.setUsername(username);

        if (user != null) {
            // Збереження користувача в сесії
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Перенаправлення на інформаційну сторінку
            response.sendRedirect("dashboard.jsp");
        } else {
            // Повернення на сторінку авторизації з помилкою
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
