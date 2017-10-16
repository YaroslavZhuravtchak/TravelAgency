package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.abstr.TourDAO;
import com.zhuravchak.domain.Tour;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yaroslav on 22-Sep-17.
 */
public class MySqlTourDAO extends TourDAO {

    private final static Logger LOG = Logger.getLogger(MySqlCityDAO.class);

    private  final String SQL_SELECT_ALL_AFTER_NOW_WITH_SEARS = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? group by tour.id ";

    private final String SQL_SELECT_ALL_VACATION_AFTER_NOW_WITH_SEARS = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='VACATION' group by tour.id";

    private  final String SQL_SELECT_ALL_SHOPPING_AFTER_NOW_WITH_SEARS = "select distinct * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='SHOPPING' group by tour.id";

    private final String SQL_SELECT_ALL_TRIP_AFTER_NOW_WITH_SEARS = "select distinct * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='TRIP' group by tour.id";

    private  final String SQL_SELECT_ALL_AFTER_NOW = "select * from tour join pass on tour.id = pass.tour_id " +
            "where  group by tour.id ";

    private final String SQL_SELECT_ALL_VACATION = "select * from tour where tour_type = 'VACATION' group by id";

    private  final String SQL_SELECT_ALL_SHOPPING = "select  * from tour where tour_type ='SHOPPING' group by id";

    private final String SQL_SELECT_ALL_TRIP = "select * from tour where tour_type ='TRIP' group by id";

    private static final String SQL_SELECT_ALL_BY_COUNTRY_ID_TOUR_AND_CITIFROME_TYPE = "select tour.id, tour.city_from, " +
            " tour.tour_type, tour.transport_type, tour.name, tour.name_ua , tour.description, tour.description_ua, " +
            "tour.duration, tour.path_image from tour join pass on tour.id = pass.tour_id join tour_city " +
            "on tour_city.tour_id = tour.id join city on city.id = tour_city.city_id join country on country.id = city.country_id " +
            " where tour.city_from = ? and tour.tour_type = ? and country_id = ? and pass.quantity_available > 0 " +
            "and pass.leaving_date > ? group by tour.id;";

    private  final String SQL_SELECT_ALL_HOT_AFTER_NOW_WITH_SEATS = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ?  and pass.isHot = true group by tour.id ";

    private  final String SQL_SELECT_ALL_AFTER_NOW_WITH_SEATS_AND_DISCOUNT = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and pass.discountForRegular > 0 and pass.isHot <> true group by tour.id ";

    private static final String SQL_SELECT_ALL_BY_CITY = "select tour.id, tour.city_from, " +
            " tour.tour_type, tour.transport_type, tour.name, tour.name_ua , tour.description, tour.description_ua, " +
            "tour.duration, tour.path_image from tour join tour_city on tour_city.tour_id = tour.id join city " +
            "on city.id = tour_city.city_id where city.id = ? group by tour.id ";

    /**
     * Constructor
     */
    public MySqlTourDAO(Connection connection) {
        super(connection);
    }

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllAfterNowWithSeats() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AFTER_NOW_WITH_SEARS)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all trip tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllTripAfterNowWithSeats() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TRIP_AFTER_NOW_WITH_SEARS)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all vacation tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllVacationAfterNowWithSeats() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_VACATION_AFTER_NOW_WITH_SEARS)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all shopping tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllShoppingAfterNowWithSeats() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_SHOPPING_AFTER_NOW_WITH_SEARS)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllAfterNowByCountryId(Long countryId, String cityfrom, String tourType) throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BY_COUNTRY_ID_TOUR_AND_CITIFROME_TYPE)) {

            statement.setString(1,cityfrom);
            statement.setString(2,tourType);
            statement.setLong(3, countryId);
            statement.setDate(4, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllAfterNow() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AFTER_NOW)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all trip tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllTrip() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TRIP)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all vacation tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllVacation() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_VACATION)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all shopping tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllShopping() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_SHOPPING)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllHotAfterNowWithSeats() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_HOT_AFTER_NOW_WITH_SEATS)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllAfterNowWithSeatsAndDiscount() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AFTER_NOW_WITH_SEATS_AND_DISCOUNT)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all trip tours after now.
     *
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Tour> getAllByCityId(Long cityId) throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BY_CITY)) {
            statement.setLong(1, cityId);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }
}
