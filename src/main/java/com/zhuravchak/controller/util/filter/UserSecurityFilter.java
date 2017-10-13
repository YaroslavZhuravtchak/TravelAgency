package com.zhuravchak.controller.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if ((role != null) && (role.equals("USER"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect(contextPath + "/login");
        }
    }
    @Override
    public void destroy() {

    }
}
