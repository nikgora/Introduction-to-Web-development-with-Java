package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.model.Account;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Account account = (Account) user.getAccounts().stream()
                .filter(a -> a.getType().equals(request.getParameter("type")) && a.getCurrency().equals(request.getParameter("currency")) && a.getBalance() == Double.parseDouble(request.getParameter("balance")))
                .findFirst()
                .orElse(null);
        if (account.getBalance() != 0) {
            request.setAttribute("message", "Account balance must be zero to delete");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            return;
        }
        try {
            user.getAccounts().remove(account);
            AccountDAO.deleteAccount(account, user);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
