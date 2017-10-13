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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AdminSearchCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Connection cn = null;
        List<Tour> tours = null;
        String page = null;
        String role = (String)request.getSession().getAttribute("role");
        Long id = null;

        page = ConfigurationManager.getProperty("path.page.tours.admin");

        String cityId = request.getParameter("cityId");
        String tourIdByName = request.getParameter("tourIdByName");
        String tourId = request.getParameter("tourId");

        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");
            TourDAO tourDAO = df.getTourDAO(cn);

            if (cityId != null){
                id = Long.valueOf(cityId);
                tours = tourDAO.getAllByCityId(id);

            } else if (tourIdByName != null){
                id = Long.valueOf(tourIdByName);
                tours = new ArrayList<>();
                tours.add(tourDAO.findEntityById(id));

            } else if (tourId != null){
                id = Long.valueOf(tourId);
                tours = new ArrayList<>();
                Tour tour = tourDAO.findEntityById(id);
                if(tour != null) {
                    tours.add(tourDAO.findEntityById(id));
                }
            }
            if (tours != null){
                TourService.getInstance().fillToursForAdmin(tours);
            }
       } catch (DAOException|ServiceException e) {
            throw new CommandException(e);
      } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }

        request.setAttribute("tours", tours);

        return page;
    }
}
