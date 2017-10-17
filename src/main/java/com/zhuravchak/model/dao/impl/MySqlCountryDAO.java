package com.zhuravchak.model.dao.impl;

import com.zhuravchak.domain.Country;
import com.zhuravchak.model.dao.abstr.CountryDAO;
import com.zhuravchak.model.exception.CountryDAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yaroslav on 25-Sep-17.
 */
public class MySqlCountryDAO extends CountryDAO {

    private final static Logger LOG = Logger.getLogger(MySqlCountryDAO.class);

    private static final String SQL_SELECT_ALL_WITH_ACTUAL_PASSES = "select country.id, country.country_name, country.country_name_ua " +
            "from country join city on country.id = city.country_id join tour_city on city.id = tour_city.city_id " +
            "join tour on tour_city.tour_id = tour.id join pass on tour.id = pass.tour_id where pass.quantity_available > 0 " +
            "and pass.leaving_date > ? group by country.id";

    /**
     * Constructor
     */
    public MySqlCountryDAO(Connection connection) {
        super(connection);
    }

    /**
     * Find all countries after now.
     *
     * @return the list
     * @throws CountryDAOException
     */
    public List<Country> getAllWithActualPasses() throws CountryDAOException {
        List<Country> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_WITH_ACTUAL_PASSES)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new CountryDAOException("getAllWithActualPasses", e);
        }
        return list;
    }


}
