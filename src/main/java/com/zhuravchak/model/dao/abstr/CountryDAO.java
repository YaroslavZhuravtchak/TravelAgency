package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.CountryDAOException;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Country;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CountryDAO.
 */
public abstract class CountryDAO extends AbstractJDBCDao<Country> {

    private final static Logger LOG = Logger.getLogger(CountryDAO.class);

    /**
     * Constructor
     */
    public CountryDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from country";
    private static final String SQL_UPDATE_ENTITY = "update country set country_name = ?, country_name_ua = ? where id = ? ";
    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from country where id = ?";
    private static final String SQL_CREATE_ENTITY = "insert into country (country_name, country_name_ua) values  (?,?)";

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public  String getSelectQuery(){
        return SQL_SELECT_ENTITY_BY_ID;
    }

    /* (non-Javadoc)
         * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
         */
    @Override
    public String getUpdateQuery(){
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
    protected void prepareStatementForUpdate(PreparedStatement ps, Country entity) throws CountryDAOException {
        try {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameUA());
            ps.setLong(3, entity.getId());
        } catch (SQLException e) {
            throw new CountryDAOException("SQL exception (prepareStatementForUpdate): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, Country entity) throws CountryDAOException {
        try {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameUA());
        } catch (SQLException e) {
            throw new CountryDAOException("SQL exception (prepareStatementForCreate): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<Country> parseResultSet (ResultSet resultSet)  throws CountryDAOException {
        List<Country> countries = new ArrayList<>();

        try {
            while (resultSet.next()) {

                Country country = new Country();

                country.setId(resultSet.getLong("id"));
                country.setName(resultSet.getString("country_name"));
                country.setNameUA(resultSet.getString("country_name_ua"));


                countries.add(country);
            }
        } catch (SQLException e) {
            throw new CountryDAOException("SQL exception (parseResultSet): " + e,e);
        }
        return countries;
    }

    /**
     * Find all countries after now with actual passes.
     *
     * @return the list
     * @throws CountryDAOException
     */
    public abstract List<Country> getAllWithActualPasses() throws CountryDAOException;
}
