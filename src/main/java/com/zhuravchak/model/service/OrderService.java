package com.zhuravchak.model.service;

import com.zhuravchak.model.dao.abstr.OrderDAO;
import com.zhuravchak.model.dao.abstr.PassDAO;
import com.zhuravchak.model.dao.abstr.UserDAO;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.model.dao.impl.MySqlOrderDAO;
import com.zhuravchak.model.dao.impl.MySqlPassDAO;
import com.zhuravchak.model.dao.impl.MySqlUserDAO;
import com.zhuravchak.domain.Order;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.domain.User;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The Class OrderService.
 */
public class OrderService {

    private final static Logger LOG = Logger.getLogger(OrderService.class);


    /**
     * Instance of  a OrderService.
     */
    private static OrderService orderService;

    /**
     * Instantiates a new OrderService.
     */
    private  OrderService(){}

    /**
     * Gets the instance.
     *
     * @return the OrderService instance
     */
    public static OrderService getInstance(){
        if(orderService == null){
            orderService = new OrderService();
        }
        return orderService;
    }

    /**
     * Calculate price of user's ofer.
     * @param  isRegular whether user is regular
     * @param  pass the pass user want to buy
     * @param  quantity the number of passes which user want to buy
     * @return price of passes
     */
    public double calculatePrice(Boolean isRegular, Pass pass, int quantity) {
        Double price = pass.getPrice()*quantity;
        if(pass.getHot()){
            price = price*0.5;
        } else if (isRegular){
            price = price - price*(pass.getDiscountForRegular()/100.0);
        }
        System.out.println(price);
       return price;
    }

    /**
     * Buy pass.
     * @param  pass the pass user want to buy
     * @param  userId user's id
     * @param  quantity the number of passes which user want to buy
     * @param  price the price
     * @throws ServiceException the Service exception
     * @return boolean bought or not
     */
    public synchronized boolean buyPass (Pass pass, long userId, int quantity, double price ) throws ServiceException{
       boolean result =false;
        Connection cn = null;
        try {

            cn = ConnectionPool.getConnection();
            DAOFactory df =  DAOFactory.getDAOFactory("MYSQL");
            PassDAO passDAO = df.getPassDAO(cn);
            OrderDAO orderDAO = df.getOrderDAO(cn);
            UserDAO userDAO = df.getUserDAO(cn);
            Pass updatedPass = passDAO.findEntityById(pass.getId());
            if(updatedPass.getQuantityAvailable() < quantity ){
                return false;
            }
            int newQuantity =  updatedPass.getQuantityAvailable() - quantity;
            cn.setAutoCommit(false);
            User user = userDAO.findEntityById(userId);
            pass.setQuantityAvailable(newQuantity);

            if(!passDAO.update(pass)) {
                throw new SQLException("Can't update Pass");
            }

            Order order = new Order();
            order.setUserId(userId);
            order.setPassId(pass.getId());
            order.setQuantity(quantity);
            order.setOrderDate(LocalDate.now());
            order.setTotalPrice(price);

            if (!orderDAO.create(order)) {
                throw new SQLException("Can't create Order");
            }
            if (UserService.getInstance().isRegular(user, quantity)) {
                user.setRegular(true);
                if(!userDAO.update(user)){
                    throw new SQLException("Can't update User");
                }
            }

            cn.commit();
            result = true;
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (SQLException e1) {
                throw  new ServiceException("buyPass - while rolling back",e1);
            }
            throw  new ServiceException("buyPass", e);
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new ServiceException("setAutoCommit(true)",e);
            }
            if(cn != null) {
                ConnectionPool.closeConnection(cn);
            }
        }
        return result;
    }
}
