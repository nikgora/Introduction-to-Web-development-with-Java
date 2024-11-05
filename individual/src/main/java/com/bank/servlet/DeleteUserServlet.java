package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            AccountDAO.deleteAccountsByUser(user);
            UserDAO.deleteUser(user.getUsername());
            response.sendRedirect(request.getContextPath() + "/logout");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
