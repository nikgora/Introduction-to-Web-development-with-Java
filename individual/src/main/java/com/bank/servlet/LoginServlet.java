package com.bank.servlet;// com.bank.servlet.LoginServlet.java

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримання параметрів з форми
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (validateUser(username, password)) {
                HttpSession session = request.getSession();
                User user = UserDAO.getUserByUsername(username);
                user.setAccounts(UserDAO.getAccountsByUser(user));
                session.setAttribute("user", user);

                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean validateUser(String username, String password) throws SQLException {
        return UserDAO.getUserByUsername(username)!= null && UserDAO.getUserByUsername(username).getPassword().equals(password);
    }
}
