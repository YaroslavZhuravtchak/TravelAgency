package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.abstr.CityDAO;
import com.zhuravchak.domain.City;
import com.zhuravchak.domain.Tour;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * Created by Yaroslav on 24-Sep-17.
 */
public class MySqlCityDAO extends CityDAO {

    private final static Logger LOG = Logger.getLogger(MySqlCityDAO.class);

    /**
     * Constructor
     */
    public MySqlCityDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ALL_BY_ENTiTY = "select city.id, city.country_id, city.city_name, city.city_name_ua " +
            "from city join tour_city on city.id = tour_city.city_id where tour_id = ?";

    /**
     * Find all city for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<City> findAllForTour(Tour tour) throws DAOException {
        List<City> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BY_ENTiTY)) {
            statement.setLong(1,tour.getId());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }
}
