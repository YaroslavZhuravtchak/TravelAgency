package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.domain.Tour;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yaroslav on 25-Sep-17.
 */
public class MySqlPassDAO extends PassDAO {

    private final static Logger LOG = Logger.getLogger(PassDAO.class);

    private static final String SQL_SELECT_ALL_FOR_TOUR_WITH_SEATS = "select distinct * from pass where tour_id = ? " +
            "and quantity_available > 0 and leaving_date > ?";

    private static final String SQL_SELECT_ALL_FOR_TOUR = "select distinct * from pass where tour_id = ? " +
            " and leaving_date > ?";

    private static final String SQL_SELECT_ALL_HOT_FOR_TOUR = "select distinct * from pass where tour_id = ? " +
            "and quantity_available > 0 and leaving_date > ? and isHot = true";

    private static final String SQL_SELECT_ALL_FOR_TOUR_WITH_DISCOUNT = "select distinct * from pass where tour_id = ? " +
            "and quantity_available > 0 and leaving_date > ? and discountForRegular > 0 and isHot <> true";

    /**
     * Constructor
     */
    public MySqlPassDAO(Connection connection) {
        super(connection);
    }

    /**
     * Find all passes for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Pass> findAllForTourWithSeats(Tour tour) throws DAOException {
        List<Pass> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FOR_TOUR_WITH_SEATS)) {

            statement.setLong(1, tour.getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Find all passes for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Pass> findAllForTour(Tour tour) throws DAOException {
        List<Pass> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FOR_TOUR)) {

            statement.setLong(1, tour.getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Find all passes for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Pass> findAllHot(Tour tour) throws DAOException {
        List<Pass> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_HOT_FOR_TOUR)) {

            statement.setLong(1, tour.getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        Collections.sort(list);
        return list;
    }

    /**
     * Find all passes for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Pass> findAllWithDiscount(Tour tour) throws DAOException {
        List<Pass> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FOR_TOUR_WITH_DISCOUNT)) {

            statement.setLong(1, tour.getId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        Collections.sort(list);
        return list;
    }
}

