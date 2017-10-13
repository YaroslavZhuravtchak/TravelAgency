package com.zhuravchak.controller.command.impl.admin;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.City;
import com.zhuravchak.domain.Country;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.domain.enums.CityFrom;
import com.zhuravchak.domain.enums.TourType;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.abstr.CityDAO;
import com.zhuravchak.model.dao.abstr.CountryDAO;
import com.zhuravchak.model.dao.abstr.TourDAO;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrepareAdminSearchCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Connection cn = null;
        String page = ConfigurationManager.getProperty("path.page.search.admin");
        List<City> cities = null;
        List<Tour> tours = null;
        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");
            CityDAO cityDAO = df.getCityDAO(cn);
            TourDAO tourDAO = df.getTourDAO(cn);

            cities = cityDAO.getAllWithTours();
            tours = tourDAO.findAll();
        } catch (DAOException e) {
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        Collections.sort(cities);
        Collections.sort(tours, new Comparator<Tour>() {
            @Override
            public int compare(Tour o1, Tour o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        request.setAttribute("cities", cities);
        request.setAttribute("tours", tours);

        return page;
    }
}
