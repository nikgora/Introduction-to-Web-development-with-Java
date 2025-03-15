package com.bank.servlet;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            // Удаление пользователя; благодаря каскаду, связанные счета также удалятся
            UserDAO.deleteUser(user);
            response.sendRedirect(request.getContextPath() + "/logout");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
