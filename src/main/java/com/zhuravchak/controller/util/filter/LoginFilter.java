package com.zhuravchak.controller.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
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

        if ((role != null) && (role.equals("ADMIN"))) {
            res.sendRedirect(contextPath +"/admin?command=forward&page=mainadmin");
        } else if ((role != null) && (role.equals("USER"))) {
            res.sendRedirect(contextPath +"/user?command=forward&page=main");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {

    }
}
