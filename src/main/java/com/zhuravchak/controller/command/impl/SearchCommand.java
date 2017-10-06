package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.City;
import com.zhuravchak.domain.Country;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.domain.enums.CityFrom;
import com.zhuravchak.domain.enums.TourType;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.model.dao.impl.MySqlCityDAO;
import com.zhuravchak.model.dao.impl.MySqlCountryDAO;
import com.zhuravchak.model.dao.impl.MySqlTourDAO;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.exception.ServiceException;
import com.zhuravchak.model.service.CountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;


/**
 * Created by Yaroslav on 05-Oct-17.
 */
public class SearchCommand  implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Connection cn = null;
        List<Tour> tours = null;
        String page = null;
        String role = (String)request.getSession().getAttribute("role");

        if(role.equals("ADMIN")){
            page = ConfigurationManager.getProperty("path.page.tours.admin");
        }else{
            page = ConfigurationManager.getProperty("path.page.tours");
        }

        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory) DAOFactory.getDAOFactory("MYSQL");
            MySqlTourDAO tourDAO = df.getTourDAO(cn);

            Long country_id = Long.valueOf(request.getParameter("countryId"));
            String cityFrom = request.getParameter("cityFrom");
            String tourType = request.getParameter("tourType");

            tours = tourDAO.getAllAfterNowByCountryId(country_id, cityFrom, tourType);

        } catch (DAOException e) {
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
