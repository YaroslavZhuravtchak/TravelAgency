package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Order;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class OrderDAO.
 */
public abstract class OrderDAO extends AbstractJDBCDao<Order> {

    private final static Logger LOG = Logger.getLogger(OrderDAO.class);

    /**
     * Constructor
     */
    public OrderDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from orders";
    private static final String SQL_UPDATE_ENTITY = "update orders set pass_id = ?, user_id = ?, quantity = ?," +
            " totalPrice = ?, order_date = ? where id = ? ";
    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from orders where id = ?";
    private static final String SQL_CREATE_ENTITY = "INSERT INTO orders (pass_id, user_id, quantity, totalPrice, order_date)" +
            " VALUES(?,?,?,?,?)";

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
    protected void prepareStatementForUpdate(PreparedStatement ps, Order entity) throws DAOException {
        try {
            ps.setLong(1, entity.getPassId());
            ps.setLong(2,entity.getUserId());
            ps.setInt(3, entity.getQuantity());
            ps.setDouble(4, entity.getTotalPrice());
            ps.setDate(5, Date.valueOf(entity.getOrderDate()));
            ps.setLong(6, entity.getId());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, Order entity) throws DAOException {
        try {
            ps.setLong(1, entity.getPassId());
            ps.setLong(2,entity.getUserId());
            ps.setInt(3, entity.getQuantity());
            ps.setDouble(4, entity.getTotalPrice());
            ps.setDate(5, Date.valueOf(entity.getOrderDate()));
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<Order> parseResultSet (ResultSet resultSet)  throws DAOException {
        List<Order> orders = new ArrayList<>();

        try {
            while (resultSet.next()) {

                Order order = new Order();

                order.setId(resultSet.getLong("id"));
                order.setPassId(resultSet.getLong("pass_id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                order.setOrderDate(resultSet.getDate("order_date").toLocalDate());

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
        return orders;
    }
}
