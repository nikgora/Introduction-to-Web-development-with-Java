package com.bank.servlet;

import com.bank.dao.AccountDAO;
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

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String currency = request.getParameter("currency");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Account account = (Account) user.getAccounts().stream()
                .filter(a -> a.getType().equals(type) && a.getCurrency().equals(currency))
                .findFirst()
                .orElse(null);
        if (amount + account.getBalance() > 10e13) {
            request.setAttribute("message", "Deposit amount exceeds the limit 10^13");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            return;
        }
        try {
            user.getAccounts().remove(account);
            account.setBalance(account.getBalance() + amount);
            AccountDAO.updateAccount(account);
            user.getAccounts().add(account);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
