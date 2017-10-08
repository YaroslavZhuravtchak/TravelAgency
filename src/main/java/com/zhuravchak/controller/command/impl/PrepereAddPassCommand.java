package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

/**
 * Created by Yaroslav on 07-Oct-17.
 */
public class PrepereAddPassCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(PrepereAddPassCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = null;
        URL url = null;
        try {
            url = new URL(request.getHeader("referer"));

            if( request.getSession().getAttribute("toursPage") == null ) {
                request.getSession().setAttribute("toursPage", url.toString());
            }

        } catch (MalformedURLException e) {
            LOG.error(e);
            throw new CommandException(e);
        }

        page = ConfigurationManager.getProperty("path.page.addpass");
        LocalDate date = LocalDate.now().plusDays(1);

        request.getSession().setAttribute("date", date );
        request.getSession().setAttribute("tourId",request.getParameter("tourId"));

        return page;
    }


}
