package com.zhuravchak.model.service;

import com.zhuravchak.model.dao.abstr.CityDAO;
import com.zhuravchak.model.dao.abstr.CountryDAO;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.model.dao.impl.MySqlCityDAO;
import com.zhuravchak.model.dao.impl.MySqlCountryDAO;
import com.zhuravchak.model.dao.impl.MySqlPassDAO;
import com.zhuravchak.domain.City;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.util.List;

/**
 * The Class TourService.
 */
public class TourService {

    private final static Logger LOG = Logger.getLogger(TourService.class);

    /**
     * Instance of  a TourService.
     */
    private static TourService tourService;

    /**
     * Instantiates a new TourService.
     */
    private  TourService(){}

    /**
     * Gets the instance.
     *
     * @return the TourService instance
     */
    public static TourService getInstance(){
        if(tourService == null){
            tourService = new TourService();
        }
        return tourService;
    }

    /**
     * Fill tours with passes and cities.
     * @param  tours the list of tours
     * @return void
     * @throws ServiceException the Service exception
     */
    public  void fillTours(List<Tour> tours) throws ServiceException{

        Connection cn = null;

        for(Tour tour: tours){
            try {
                cn = ConnectionPool.getConnection();
                DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
                PassDAO passDAO = df.getPassDAO(cn);
                CityDAO cityDAO = df.getCityDAO(cn);
                CountryDAO countryDAO = df.getCountryDAO(cn);
                tour.setPasses(passDAO.findAllForTour(tour));
                List<City> cities = cityDAO.findAllForTour(tour);
                tour.setCities(cities);
                for(City city : cities){
                    city.setCountry(countryDAO.findEntityById(city.getCountryId()));
                }
            } catch (Exception e) {
                throw new ServiceException(e);
            }  finally {
                if(cn != null){
                    ConnectionPool.closeConnection(cn);
                }
            }
        }
    }
}
