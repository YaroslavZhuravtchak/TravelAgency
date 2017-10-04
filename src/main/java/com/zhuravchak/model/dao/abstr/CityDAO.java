package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.City;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CityDAO.
 */
public abstract class CityDAO extends AbstractJDBCDao<City> {

    private final static Logger LOG = Logger.getLogger(CityDAO.class);

    /**
     * Constructor
     */
    public CityDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from city";
    private static final String SQL_UPDATE_ENTITY = "update city set country_id = ?, city_name = ?, city_name_ua = ? where id = ? ";
    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from city where id = ?";
    private static final String SQL_CREATE_ENTITY = "insert into city (country_id, city_name, city_name_ua) values (?,?,?)";

    /* (non-Javadoc)
         * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
         */
    @Override
    public String getSelectQuery() {
        return SQL_SELECT_ENTITY_BY_ID;
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public String getUpdateQuery() {
        return SQL_UPDATE_ENTITY;
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public String getDeleteQuery() {
        return SQL_DELETE_ENTITY_BY_ID;
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public String getCreateQuery() {
        return SQL_CREATE_ENTITY;
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForUpdate(PreparedStatement ps, City entity) throws DAOException {
        try {
            ps.setLong(1, entity.getCountryId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getNameUA());
            ps.setLong(4, entity.getId());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e, e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, City entity) throws DAOException {
        try {
            ps.setLong(1, entity.getCountryId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getNameUA());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e, e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<City> parseResultSet(ResultSet resultSet) throws DAOException {
        List<City> cities = new ArrayList<>();

        try {
            while (resultSet.next()) {

                City city = new City();

                city.setId(resultSet.getInt("id"));
                city.setCountryId(resultSet.getInt("country_id"));
                city.setName(resultSet.getString("city_name"));
                city.setNameUA(resultSet.getString("city_name_ua"));

                cities.add(city);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e, e);
        }
        return cities;
    }
}
