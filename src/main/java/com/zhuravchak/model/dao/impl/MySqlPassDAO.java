package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.domain.Tour;
import org.apache.log4j.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yaroslav on 25-Sep-17.
 */
public class MySqlPassDAO extends PassDAO {

    private final static Logger LOG = Logger.getLogger(PassDAO.class);

    private static final String SQL_SELECT_ALL_FOR_TOUR = "select distinct * from pass where tour_id = ? " +
            "and quantity_available > 0 and leaving_date > ?";

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
        return list;
    }
}

