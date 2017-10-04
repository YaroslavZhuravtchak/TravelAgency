package com.zhuravchak.model.dao.factory;

import com.zhuravchak.model.dao.impl.*;
import java.sql.Connection;

/**
 * The Class MySqlDaoFactory.
 */
public class MySqlDaoFactory implements DAOFactory {

    /**
     * Instance of  a MySqlDaoFactory.
     */
    private static MySqlDaoFactory factory;

    /**
     * Instantiates a new MySqlDaoFactory.
     */
    private  MySqlDaoFactory(){}

    /**
     * Gets the instance.
     *
     * @return the MySqlDaoFactory instance
     */
    public static MySqlDaoFactory getInstance(){
        if(factory == null){
            factory = new MySqlDaoFactory();
        }
        return factory;
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public  MySqlCityDAO getCityDAO(Connection connection) {
        return new MySqlCityDAO(connection) ;
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public MySqlCountryDAO getCountryDAO(Connection connection) {
        return new MySqlCountryDAO(connection);
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public MySqlUserDAO getUserDAO(Connection connection) {
        return new MySqlUserDAO(connection);
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public MySqlTourDAO getTourDAO(Connection connection) {
        return new MySqlTourDAO(connection);
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public MySqlPassDAO getPassDAO(Connection connection) {
        return new MySqlPassDAO(connection);
    }

    /* (non-Javadoc)
       * @see com.zhuravchak.model.dao.factory.DAOFactory
       */
    @Override
    public MySqlOrderDAO getOrderDAO(Connection connection) {
        return new MySqlOrderDAO(connection);
    }
}

