package com.zhuravchak.controller.command.impl.user;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * The Class OrderCommand.
 */
public class OrderCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(OrderCommand.class);

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Pass pass = null;
        Tour tour = null;
        String page = null;
        Long id = Long.valueOf(request.getParameter("id"));
        Connection cn = null;

        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
            pass = df.getPassDAO(cn).findEntityById(id);
            tour = df.getTourDAO(cn).findEntityById(pass.getTourId());
        } catch (DAOException e) {
            LOG.error(e);
           throw new CommandException(e);
        }  finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        page = ConfigurationManager.getProperty("path.page.order");
        request.getSession().setAttribute("pass", pass);
        request.getSession().setAttribute("tour", tour);

        return page;
    }
}
