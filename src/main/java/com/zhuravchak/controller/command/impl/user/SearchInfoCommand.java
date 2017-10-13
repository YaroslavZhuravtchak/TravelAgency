package com.zhuravchak.controller.command.impl.user;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.Country;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.domain.enums.CityFrom;
import com.zhuravchak.domain.enums.TourType;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.abstr.CountryDAO;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.model.dao.impl.MySqlCountryDAO;
import com.zhuravchak.model.dao.impl.MySqlTourDAO;
import com.zhuravchak.model.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Yaroslav on 07-Oct-17.
 */
public class SearchInfoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Connection cn = null;
        String page = ConfigurationManager.getProperty("path.page.search");
        List<Country> countries = null;
        List<Tour> tours = null;
        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");
            CountryDAO countryDAO = df.getCountryDAO(cn);

            countries = countryDAO.getAllWithActualPasses();

        } catch (DAOException e) {
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }

        CityFrom[] cities = CityFrom.values();
        TourType[] tourTypes = TourType.values();

        request.setAttribute("countries", countries);
        request.setAttribute("citiesFrom", cities);
        request.setAttribute("tourTypes", tourTypes);

        return page;
    }
}
