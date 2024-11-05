package com.bank.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        var requestURI = httpRequest.getRequestURI();
        boolean loginRequest = requestURI.equals(httpRequest.getContextPath() + "/login.jsp") ||
                requestURI.equals(httpRequest.getContextPath() + "/login") || requestURI.equals(httpRequest.getContextPath() + "/register.jsp") || requestURI.equals(httpRequest.getContextPath() + "/register")|| requestURI.equals("jdbc:postgresql://localhost:5433/Java");



        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}