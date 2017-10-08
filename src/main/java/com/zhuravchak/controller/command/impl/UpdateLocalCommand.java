package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Class UpdateLocalCommand.
 */
public class UpdateLocalCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(UpdateLocalCommand.class);

      /* (non-Javadoc)
       * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
       */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession(true);
        String page = ConfigurationManager.getProperty("path.page.redirect");
        URL url = null;
        try {
            url = new URL(request.getHeader("referer"));
           // request.setAttribute("redirect", "controller?" + url.getQuery());

        } catch (MalformedURLException e) {
            LOG.error(e);
            throw new CommandException(e);
        }

        String local = request.getParameter("local");
        session.setAttribute("lang", local);

        String role = (String)session.getAttribute("role");
        String query = url.getQuery();
        String redir = null;

        if ((query != null && query.equals("command=logout&page=logout")) || ( role == null && query==null)) {
             redir = "controller";
        } else if (query == null && role != null ) {
            redir = "controller?command=forward&page=main";
        } else {
            redir = url.toString();
        }

        request.setAttribute("redir", redir);

        return  page ;
    }
}
