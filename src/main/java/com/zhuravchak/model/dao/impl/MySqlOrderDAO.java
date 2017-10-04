package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.abstr.OrderDAO;
import com.zhuravchak.domain.Order;
import com.zhuravchak.domain.Tour;
import com.zhuravchak.domain.User;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.List;

/**
 * Created by Yaroslav on 26-Sep-17.
 */
public class MySqlOrderDAO extends OrderDAO {

    private final static Logger LOG = Logger.getLogger(MySqlOrderDAO.class);

    private static final String SQL_SELECT_ALL_FOR_TOUR = "select * from orders where tour_id = ?";
    private static final String SQL_SELECT_ALL_FOR_USER = "select * from orders where user_id = ?";

    /**
     * Constructor
     */
    public MySqlOrderDAO(Connection connection) {
        super(connection);
    }


    /**
     * Find all orders for user.
     * @param  user the user
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Order> findAllForUser(User user) throws DAOException {
        List<Order> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FOR_USER)) {
            statement.setLong(1,user.getId());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * Find all orders for tour.
     * @param  tour the tour
     * @return the list
     * @throws DAOException the DAO exception
     */
    public List<Order> findAllForTour(Tour tour) throws DAOException {
        List<Order> list;
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FOR_TOUR)) {
            statement.setLong(1, tour.getId());
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }
}