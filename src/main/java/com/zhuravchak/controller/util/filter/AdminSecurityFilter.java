package com.zhuravchak.controller.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminSecurityFilter implements Filter {

    /**
     * Inits.
     *
     * @param filterConfig the f config
     * @throws ServletException the maincontroler exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Do filter.
     *
     * @param servletRequest the request
     * @param servletResponse the response
     * @param filterChain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the maincontroler exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if ((role != null) && (role.equals("ADMIN"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect("/login");
        }
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {

    }
}
