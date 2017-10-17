package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.domain.User;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.controller.util.resource.MessageManager;
import com.zhuravchak.model.service.UserService;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * The Class LoginCommand.
 */
public class LoginCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response)throws CommandException {
            String page = null;

            String login = request.getParameter(PARAM_NAME_LOGIN);
            String pass = request.getParameter(PARAM_NAME_PASSWORD);

        Connection cn = null;
        HttpSession session = null;
            try {
                cn = ConnectionPool.getConnection();
                DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
                if (UserService.getInstance().checkUser(login, pass)) {

                    User user = df.getUserDAO(cn).findEntityByLogin(login);
                    session = request.getSession();
                    System.out.println(session.getAttribute("lang"));
                    if (session.getAttribute("user") == null) {
                        session.setAttribute("user", user.getLogin());
                    }
                    if (session.getAttribute("role") == null) {
                        session.setAttribute("role", user.getRole().toString());
                    }
                    if (session.getAttribute("iduser") == null) {
                        session.setAttribute("iduser", user.getId());
                    }
                    if (session.getAttribute("isRegular") == null) {
                        session.setAttribute("isRegular", String.valueOf(user.isRegular()));
                    }
                    if(session.getAttribute("role") == "USER" ) {
                        page = ConfigurationManager.getProperty("path.page.main");
                    } else if(session.getAttribute("role") == "ADMIN" ){
                        page = ConfigurationManager.getProperty("path.page.main.admin");
                    }

                    LOG.info("User: " + session.getAttribute("user") +
                            " Role: " + session.getAttribute("role") + " log in");
                } else {
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.loginerror"));
                    page = ConfigurationManager.getProperty("path.page.login");
                }
            } catch (ServiceException|DAOException e) {
               LOG.error(e);
               throw new CommandException(e);
            } finally {
                if(cn != null){
                    ConnectionPool.closeConnection(cn);
                }
            }
        return page;
    }
}