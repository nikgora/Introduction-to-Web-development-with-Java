import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String loginURI = req.getContextPath() + "/login.jsp";

        String requestURI = req.getRequestURI();

        if (requestURI.contains("?")) {
            requestURI = requestURI.substring(0, requestURI.indexOf("?"));
        }


        boolean loggedIn = req.getSession(false) != null && req.getSession(false).getAttribute("user") != null;
        boolean loginRequest = requestURI.equals(loginURI) || requestURI.endsWith("/login") || requestURI.endsWith("/login.jsp");

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }

}
