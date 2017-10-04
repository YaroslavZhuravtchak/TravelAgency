package com.zhuravchak.controller;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.factory.ActionFactory;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TravelController extends HttpServlet {

    private final static Logger LOG = Logger.getLogger(TravelController.class);

    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the maincontroler exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the maincontroler exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the maincontroler exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request, response);
        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            LOG.error(e);
        }
        if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);

        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}