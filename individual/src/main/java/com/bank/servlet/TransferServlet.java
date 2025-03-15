package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import com.bank.model.User;
import com.bank.util.ExchangeRateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromAccountType = request.getParameter("fromAccount");
        String toAccountType = request.getParameter("toAccount");
        double amount = Double.parseDouble(request.getParameter("amount"));
        User user = (User) request.getSession().getAttribute("user");

        try {
            Account from = user.getAccounts().stream()
                    .filter(a -> a.getType().equals(fromAccountType))
                    .findFirst()
                    .orElse(null);
            Account to = user.getAccounts().stream()
                    .filter(a -> a.getType().equals(toAccountType))
                    .findFirst()
                    .orElse(null);

            if (amount > from.getBalance()) {
                request.setAttribute("message", "Insufficient balance");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                return;
            }
            double rate = from.getCurrency().equals(to.getCurrency()) ? 1 :
                    ExchangeRateUtil.getExchangeRate(from.getCurrency(), to.getCurrency());
            double convertedAmount = amount * rate;
            if (convertedAmount + to.getBalance() > 1e13) {
                request.setAttribute("message", "Deposit amount exceeds the limit 10^13");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
                return;
            }
            if (AccountDAO.transferMoney(fromAccountType, toAccountType, user, amount)) {
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + convertedAmount);
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                request.setAttribute("message", "Transfer failed");
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
