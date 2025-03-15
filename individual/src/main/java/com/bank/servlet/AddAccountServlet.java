package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addAccount")
public class AddAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        double balance = Double.parseDouble(request.getParameter("balance"));
        String currency = request.getParameter("currency");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (AccountDAO.isExist(user, type)) {
            request.setAttribute("message", "Account already exists");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            return;
        }

        Account account = new Account(type, balance, currency);
        try {
            AccountDAO.createAccount(account, user);
            // Обновляем список счетов пользователя
            user.getAccounts().add(account);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
