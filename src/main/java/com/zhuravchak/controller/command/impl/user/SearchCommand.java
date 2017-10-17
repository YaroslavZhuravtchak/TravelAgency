package com.zhuravchak.controller.command.impl.user;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;


public class SearchCommand  implements ActionCommand {

    /* (non-Javadoc)
       * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
       */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Connection cn = null;
        List<Tour> tours = null;
        String page = ConfigurationManager.getProperty("path.page.tours");
        String role = (String)request.getSession().getAttribute("role");

        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");
            TourDAO tourDAO = df.getTourDAO(cn);

            Long country_id = Long.valueOf(request.getParameter("countryId"));
            String cityFrom = request.getParameter("cityFrom");
            String tourType = request.getParameter("tourType");

            tours = tourDAO.getAllAfterNowByCountryId(country_id, cityFrom, tourType);
            if (tours != null){
                TourService.getInstance().fillToursForUser(tours);
            }
        } catch (DAOException|ServiceException e) {
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        Collections.sort(tours);
        request.setAttribute("tours", tours);

   return page;
    }
}
