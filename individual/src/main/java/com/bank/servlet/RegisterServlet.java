package com.bank.servlet;// com.bank.servlet.RegisterServlet.java

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.bank.dao.UserDAO;
import com.bank.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_USERNAME_LENGTH = 6;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримання параметрів з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // Collect error messages
        List<String> errors = new ArrayList<>();

        // Validate email format
        if (!isValidEmail(email)) {
            errors.add("Invalid email format");
        }

        // Validate username length
        if (username.length() < MIN_USERNAME_LENGTH) {
            errors.add("Username must be at least " + MIN_USERNAME_LENGTH + " characters long");
        }

        // Validate password length
        if (password.length() < MIN_PASSWORD_LENGTH) {
            errors.add("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }

        // If there are errors, forward back to the registration page
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        // Створення об'єкту com.bank.model.User
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // Збереження користувача (логіка збереження в базу даних)
        try {
            UserDAO.saveUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Перенаправлення на сторінку авторизації
        response.sendRedirect("login.jsp");
    }
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}


