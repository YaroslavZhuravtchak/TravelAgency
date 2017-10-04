package com.zhuravchak.model.dao.factory;

import com.zhuravchak.model.dao.abstr.*;
import java.sql.Connection;

/**
 * The Interface DAOFactory.
 */
public interface DAOFactory {

    /**
     * Find CityDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public CityDAO getCityDAO(Connection connection);

    /**
     * Find CountryDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public CountryDAO getCountryDAO(Connection connection);

    /**
     * Find UserDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public UserDAO getUserDAO(Connection connection);

    /**
     * Find TourDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public TourDAO getTourDAO(Connection connection);

    /**
     * Find PassDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public PassDAO getPassDAO(Connection connection);

    /**
     * Find OrderDAO.
     * @param  connection the connection
     * @return the DAO
     */
    public OrderDAO getOrderDAO(Connection connection);

    /**
     * Find DAOFactory.
     * @param  whichFactory String
     * @return the DAOFactory
     */
    public static DAOFactory getDAOFactory(String whichFactory) {
        switch (whichFactory) {
            case "MYSQL":
                return MySqlDaoFactory.getInstance();
            default:
                return null;
        }
    }
}
