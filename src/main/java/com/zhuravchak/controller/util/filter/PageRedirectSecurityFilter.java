package com.zhuravchak.controller.util.filter;

import com.zhuravchak.controller.util.resource.ConfigurationManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    /**
     * The Class PageRedirectSecurityFilter.
     */
    public class PageRedirectSecurityFilter implements Filter {

    /** The indexPath. */
        private String indexPath;

    /**
     * Inits the.
     *
     * @param fConfig the f config
     * @throws ServletException the maincontroler exception
     */
        public void init(FilterConfig fConfig) throws ServletException {
            indexPath = ConfigurationManager.getProperty("path.page.index");
        }

    /**
     * Do filter.
     *
     * @param request the request
     * @param response the response
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the maincontroler exception
     */
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
            chain.doFilter(request, response);
        }

    /**
     * Destroy.
     */
        public void destroy() {}
    }
