package com.bank;// com.bank.LoginServlet.java

import java.io.IOException;
import java.util.ArrayList;

import com.bank.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримання параметрів з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (validateUser(username, password)) {
            HttpSession session = request.getSession();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail("13@gmail.com");
            user.setAccounts(new ArrayList<>());
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid");
        }
    }
    private boolean validateUser(String username, String password) {
        return !username.isEmpty() && "1".equals(password);
    }
}
