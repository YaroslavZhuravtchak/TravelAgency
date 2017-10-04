package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

/**
 * The Class MakeHotCommand.
 */
public class MakeHotCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(MakeHotCommand.class);

    /* (non-Javadoc)
   * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
   */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        URL url = null;
        Long id = Long.valueOf(request.getParameter("id"));
        Connection cn = null;
        String page = null;

        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory) DAOFactory.getDAOFactory("MYSQL");
            PassDAO passDAO = df.getPassDAO(cn);
            url = new URL(request.getHeader("referer"));
            String redir = url.toString() + "#" + request.getParameter("anchor");
            request.setAttribute("redir", redir);

            Pass pass = passDAO.findEntityById(id);
            pass.setHot(true);
           if( passDAO.update(pass)){
               LOG.info("Pass (id = " + pass.getId() + ") was made hot");
           } else {
               LOG.info("Pass (id = " + pass.getId() + "): failed to make hot");
           }
        } catch (DAOException | MalformedURLException e) {
            LOG.error(e);
            throw new CommandException(e);
        }  finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        page =  ConfigurationManager.getProperty("path.page.redirect");
        return page;
    }
}
