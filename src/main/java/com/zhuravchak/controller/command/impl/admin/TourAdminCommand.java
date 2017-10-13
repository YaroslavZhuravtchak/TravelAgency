package com.zhuravchak.controller.command.impl.admin;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.abstr.TourDAO;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.exception.ServiceException;
import com.zhuravchak.model.service.TourService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

public class TourAdminCommand implements ActionCommand {
    private final static Logger LOG = Logger.getLogger(TourAdminCommand.class);

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        request.getSession().setAttribute("toursPage", null);
        String page = null;
        String role = (String) request.getSession().getAttribute("role");
        if (role == null) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else if (role.equals("ADMIN")) {
            page = ConfigurationManager.getProperty("path.page.tours.admin");
        }

        List<Tour> tours = null;
        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
            TourDAO tourDAO = df.getTourDAO(cn);
            String type = request.getParameter("type");

            if (type.equals("shopping")) {
                tours = tourDAO.getAllShopping();
            } else if (type.equals("trip")) {
                tours = tourDAO.getAllTrip();
            } else if (type.equals("vacation")) {
                tours = tourDAO.getAllVacation();
            }
            TourService.getInstance().fillToursForAdmin(tours);
        } catch (DAOException|ServiceException e) {
            throw new CommandException(e);
        } finally {
            if (cn != null) {
                ConnectionPool.closeConnection(cn);
            }
        }
       // Collections.sort(tours);
        request.setAttribute("tours", tours);

        return page;
    }
}
