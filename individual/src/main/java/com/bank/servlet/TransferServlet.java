package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import com.bank.model.User;
import com.bank.util.ExchangeRateUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromAccount = request.getParameter("fromAccount");
        String toAccount = request.getParameter("toAccount");
        String amountStr = request.getParameter("amount");
        double amount = Double.parseDouble(amountStr);
        User user = (User) request.getSession().getAttribute("user");
        try {
            Account from = user.getAccounts().stream()
                    .filter(a -> a.getType().equals(fromAccount))
                    .findFirst()
                    .orElse(null);
            Account to = user.getAccounts().stream()
                    .filter(a -> a.getType().equals(toAccount))
                    .findFirst()
                    .orElse(null);
            if (amount > from.getBalance()) {
                request.setAttribute("message", "Insufficient balance");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                return;
            }
            double rate = from.getCurrency().equals(to.getCurrency()) ? 1 : ExchangeRateUtil.getExchangeRate(from.getCurrency(), to.getCurrency());
            double convertedAmount = amount * rate;
            if (convertedAmount + to.getBalance() > 10e13) {
                request.setAttribute("message", "Deposit amount exceeds the limit 10^13");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                return;
            }
            if (AccountDAO.transferMoney(fromAccount, toAccount, user, amount)) {
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + convertedAmount);
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                return;
            } else {
                request.setAttribute("message", "Transfer failed");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}