package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.model.Account;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Account account = user.getAccounts().stream()
                .filter(a -> a.getType().equals(request.getParameter("type"))
                        && a.getCurrency().equals(request.getParameter("currency"))
                        && a.getBalance() == Double.parseDouble(request.getParameter("balance")))
                .findFirst()
                .orElse(null);

        if (account.getBalance() != 0) {
            request.setAttribute("message", "Account balance must be zero to delete");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            return;
        }
        try {
            // Удаляем счёт из коллекции пользователя (метод removeAccount в User)
            user.removeAccount(account);
            // Обновляем пользователя, чтобы каскадом удалить orphan-счёт
            UserDAO.updateUser(user);
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

