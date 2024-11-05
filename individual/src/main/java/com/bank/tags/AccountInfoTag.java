package com.bank.tags;// com.bank.tags.AccountInfoTag.java

import java.io.IOException;

import com.bank.model.Account;
import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import com.bank.model.User;

public class AccountInfoTag extends SimpleTagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void doTag() throws JspException, IOException {
        if (user != null) {
            JspWriter out = getJspContext().getOut();
            out.print("<p>Користувач: " + user.getUsername() + "</p>");
            out.print("<p>Email: " + user.getEmail() + "</p>");
            out.print("<P>Сума на усіх рахунках: " + user.getAccounts().stream().mapToDouble(Account::getBalance).sum() + "</p>");
        }
        else  {
            getJspContext().getOut().print("Користувач не знайдений");
        }
    }
}
