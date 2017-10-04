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

    private  final String SQL_SELECT_ALL_AFTER_NOW = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? group by tour.id ";

    private final String SQL_SELECT_ALL_VACATION_AFTER_NOW = "select * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='VACATION' group by tour.id";

    private  final String SQL_SELECT_ALL_SHOPPING_AFTER_NOW = "select distinct * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='SHOPPING' group by tour.id";

    private final String SQL_SELECT_ALL_TRIP_AFTER_NOW = "select distinct * from tour join pass on tour.id = pass.tour_id " +
            "where pass.quantity_available > 0 and pass.leaving_date > ? and tour_type ='TRIP' group by tour.id";

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
    public List<Tour> getAllTripAfterNow() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TRIP_AFTER_NOW)) {
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
    public List<Tour> getAllVacationAfterNow() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_VACATION_AFTER_NOW)) {
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
    public List<Tour> getAllShoppingAfterNow() throws DAOException {
        List<Tour> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_SHOPPING_AFTER_NOW)) {
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }


}
