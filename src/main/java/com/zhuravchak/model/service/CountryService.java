package com.zhuravchak.model.service;

import com.zhuravchak.domain.City;
import com.zhuravchak.domain.Country;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.model.dao.impl.MySqlCityDAO;
import com.zhuravchak.model.dao.impl.MySqlCountryDAO;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * The Class CountryService.
 */
public class CountryService {

    private final static Logger LOG = Logger.getLogger(CountryService.class);


    /**
     * Instance of  a CountryService.
     */
    private static CountryService countryService;

    /**
     * Instantiates a new CountryService.
     */
    private  CountryService(){}

    /**
     * Gets the instance.
     *
     * @return the CountryService instance
     */
    public static CountryService getInstance(){
        if(countryService == null){
            countryService = new CountryService();
        }
        return countryService;
    }

    /**
     * Fill countries with  cities.
     * @param  countries the list of countries
     * @return void
     * @throws ServiceException the Service exception
     */
    public  void fillCountriesWithCities(List<Country> countries) throws ServiceException {

        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory) DAOFactory.getDAOFactory("MYSQL");
            MySqlCityDAO cityDAO = df.getCityDAO(cn);

            for (Country country : countries) {
                country.setCities(cityDAO.getAllWithActualPassesForCountry(country));
            }
            } catch(Exception e){
                throw new ServiceException(e);
            }  finally{
                if (cn != null) {
                    ConnectionPool.closeConnection(cn);
                }
            }
    }
}


