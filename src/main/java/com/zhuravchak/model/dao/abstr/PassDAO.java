package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Pass;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PassDAO.
 */
public abstract class PassDAO extends AbstractJDBCDao<Pass> {

    private final static Logger LOG = Logger.getLogger(com.zhuravchak.model.dao.abstr.PassDAO.class);

    /**
     * Constructor
     */
    public PassDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from pass";
    private static final String SQL_UPDATE_ENTITY = "update pass set quantity_available = ?, isHot = ?, discountForRegular = ? where id = ? ";
    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from pass where id = ?";
    private static final String SQL_CREATE_ENTITY = "insert into pass (tour_id, leaving_date, quantity_available,  price) values (?, ?, ?, ?)";

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
    protected void prepareStatementForUpdate(PreparedStatement ps, Pass entity) throws DAOException {
        try {
            ps.setInt(1, entity.getQuantityAvailable());
            ps.setBoolean(2,entity.getHot());
            ps.setInt(3, entity.getDiscountForRegular());
            ps.setLong(4, entity.getId());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, Pass entity) throws DAOException {
        try {
            ps.setLong(1, entity.getTourId());
            ps.setDate(2, Date.valueOf(entity.getLeavingDate()));
            ps.setInt(3, entity.getQuantityAvailable());
            ps.setDouble(4, entity.getPrice());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<Pass> parseResultSet (ResultSet resultSet)  throws DAOException {
        List<Pass> passList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Pass pass = new Pass();
                pass.setId(resultSet.getLong("id"));
                pass.setTourId(resultSet.getLong("tour_id"));
                pass.setLeavingDate(resultSet.getDate("leaving_date").toLocalDate());
                pass.setQuantityAvailable(resultSet.getInt("quantity_available"));
                pass.setPrice(resultSet.getDouble("price"));
                pass.setHot(resultSet.getBoolean("isHot"));
                pass.setDiscountForRegular(resultSet.getInt("discountForRegular"));
                passList.add(pass);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
        return passList;
    }
}