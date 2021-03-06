package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.domain.enums.CityFrom;
import com.zhuravchak.domain.enums.TourType;
import com.zhuravchak.domain.enums.TransportType;
import com.zhuravchak.model.exception.TourDAOException;
import com.zhuravchak.model.service.TourService;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TourDAO.
 */
public abstract class TourDAO extends AbstractJDBCDao<Tour> {

    private final static Logger LOG = Logger.getLogger(TourDAO.class);

    /**
     * Constructor
     */
    public TourDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from tour";

    private static final String SQL_UPDATE_ENTITY = "update tour set city_from = ?, tour_type = ?, transport_type = ?, " +
            "name = ?, name_ua = ?, description = ?, description_ua = ?, duration = ?, path_image where id = ? where id = ?";

    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from tour where id = ?";

    private static final String SQL_CREATE_ENTITY = "insert into tour (city_from, tour_type, transport_type, name, name_ua," +
            " description, description_ua, duration, path_image) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";


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
    protected void prepareStatementForUpdate(PreparedStatement ps, Tour entity) throws TourDAOException {
        try {
            ps.setString(1, entity.getCityFrom().toString());
            ps.setString(2, entity.getTourType().toString());
            ps.setString(3, entity.getTransportType().toString());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getNameUA());
            ps.setString(6, entity.getDescription());
            ps.setString(7, entity.getDescriptionUA());
            ps.setInt(8, entity.getDuration());
            ps.setString(9, entity.getPathImage());
            ps.setLong(10, entity.getId());

        } catch (SQLException e) {
            throw new TourDAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, Tour entity) throws TourDAOException {
        try {
            ps.setString(1, entity.getCityFrom().toString());
            ps.setString(2, entity.getTourType().toString());
            ps.setString(3, entity.getTransportType().toString());
            ps.setString(4, entity.getName());
            ps.setString(5, entity.getNameUA());
            ps.setString(6, entity.getDescription());
            ps.setString(7, entity.getDescriptionUA());
            ps.setInt(8, entity.getDuration());
            ps.setString(9, entity.getPathImage());
        } catch (SQLException e) {
            throw new TourDAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<Tour> parseResultSet (ResultSet resultSet)  throws TourDAOException {
        List<Tour> tours = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Tour tour = new Tour();

                tour.setId(resultSet.getLong("id"));
                tour.setCityFrom(CityFrom.valueOf(resultSet.getString("city_from").toUpperCase()));
                tour.setTourType(TourType.valueOf(resultSet.getString("tour_type".toUpperCase())));
                tour.setTransportType(TransportType.valueOf(resultSet.getString("transport_type".toUpperCase())));
                tour.setName(resultSet.getString("name"));
                tour.setNameUA(resultSet.getString("name_ua"));
                tour.setDescription(resultSet.getString("description"));
                tour.setDescriptionUA(resultSet.getString("description_ua"));
                tour.setDuration(resultSet.getInt("duration"));
                tour.setPathImage(resultSet.getString("path_image"));

                tours.add(tour);
            }
        } catch (SQLException e) {
            throw new TourDAOException("SQL exception (request or table failed): " + e,e);
        }
        return tours;
    }

    /**
     * Find all tours after now with seats.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllAfterNowWithSeats() throws TourDAOException;

    /**
     * Find all trip tours after now with seats.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllTripAfterNowWithSeats() throws TourDAOException;

    /**
     * Find all vacation tours after now with seats.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllVacationAfterNowWithSeats() throws TourDAOException;

    /**
     * Find all shopping tours after now with seats.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllShoppingAfterNowWithSeats() throws TourDAOException;

    /**
     * Find all tours after now by param.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllAfterNowByCountryId(Long countryId, String cityfrom, String tourType) throws TourDAOException;

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllAfterNow() throws TourDAOException ;

    /**
     * Find all trip tours.
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllTrip() throws TourDAOException ;

    /**
     * Find all vacation tours .
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllVacation() throws TourDAOException ;

    /**
     * Find all shopping tours .
     *
     * @return the list
     * @throws TourDAOException
     */
    public  abstract List<Tour> getAllShopping() throws TourDAOException ;

    /**
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllAfterNowWithSeatsAndDiscount() throws TourDAOException ;

    /**
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllHotAfterNowWithSeats() throws TourDAOException ;


    /**
     *
     * @return the list
     * @throws TourDAOException
     */
    public abstract List<Tour> getAllByCityId(Long cityId) throws TourDAOException ;
}
