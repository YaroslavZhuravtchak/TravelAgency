package com.zhuravchak.controller.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {

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
        HttpSession session = req.getSession();

        String lang = (String) session.getAttribute("lang");

        if(lang == null || lang.equals("")){
            session.setAttribute("lang", "en_US");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {

    }
}
