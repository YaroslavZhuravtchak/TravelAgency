package com.zhuravchak.controller.command.impl.admin;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class SetQuantityCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(SetDiscountCommand.class);

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.redirect");
        URL url = null;
        Long id = Long.valueOf(request.getParameter("id"));
        int quantity = Integer.valueOf(request.getParameter("quantity"));

        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");

            PassDAO passDAO = df.getPassDAO(cn);
            url = new URL(request.getHeader("referer"));
            String redir = url.toString()+ "#" + request.getParameter("anchor");
            request.setAttribute("redir", redir);

            Pass pass = passDAO.findEntityById(id);
            pass.setQuantityAvailable(quantity);
            if(passDAO.update(pass)){
                LOG.info("Pass (id = " + pass.getId() + ") was set quantity of " + quantity +" seats");
            }
        } catch (DAOException | MalformedURLException e) {
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
