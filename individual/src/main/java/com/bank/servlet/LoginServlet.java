package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (validateUser(username, password)) {
                HttpSession session = request.getSession();
                User user = UserDAO.getUserByUsername(username);
                if (user != null) {
                    user.setAccounts(AccountDAO.getAccountsByUser(user));
                }
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private boolean validateUser(String username, String password) {
        User user = UserDAO.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
