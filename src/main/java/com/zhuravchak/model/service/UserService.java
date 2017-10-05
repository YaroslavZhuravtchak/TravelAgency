package com.zhuravchak.model.service;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.dao.factory.MySqlDaoFactory;
import com.zhuravchak.domain.Order;
import com.zhuravchak.domain.User;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.exception.ServiceException;
import com.zhuravchak.model.utill.Hashing;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.util.List;

/**
 * The Class UserService.
 */
public class UserService {

    private final static Logger LOG = Logger.getLogger(UserService.class);
    private final static int REGULAR_LIMIT = 10;

    /**
     * Instance of  a UserService.
     */
    private static UserService userService;

    /**
     * Instantiates a new UserService.
     */
    private  UserService(){}

    /**
     * Gets the instance.
     *
     * @return the UserService instance
     */
    public static UserService getInstance(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    /**
     * Check user's login and password.
     * @param  enterLogin the user's entered login
     * @param  enterPass the user's entered password
     * @return boolean
     * @throws ServiceException the Service exception
     */
    public boolean checkUser(String enterLogin, String enterPass) throws ServiceException {
        User user = null;
        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory)DAOFactory.getDAOFactory("MYSQL");
            user = df.getUserDAO(cn).findEntityByLogin(enterLogin);
            System.out.println(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }  finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        if(user==null) return false;

        return user.getLogin().equals(enterLogin) &&
                checkPassword(user, enterPass);
    }

    /**
     * Check user's password.
     * @param  user the user
     * @param  enterPass the user's entered password
     * @return boolean
     */
    public boolean checkPassword(User user,  String enterPass) {
        String hashedPassword = Hashing.sha256(Hashing.saltPassword(enterPass, Hashing.hexStringToByte(user.getSalt())));
        return user.getPassword().equals(hashedPassword);
    }

    /**
     * Check user's login.
     * @param  enterLogin the user's entered login
     * @return boolean
     * @throws ServiceException the Service exception
     */
    public boolean checkLogin(String enterLogin) throws ServiceException {
        User user = null;
        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory)DAOFactory.getDAOFactory("MYSQL");
            user = df.getUserDAO(cn).findEntityByLogin(enterLogin);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        return user==null;
    }

    /**
     * Check whether user is regular.
     * @param  user the user
     * @param  quantityBought the quantity of pass which user try yo buy
     * @return boolean
     * @throws ServiceException the Service exception
     */
    public boolean isRegular(User user, int quantityBought) throws ServiceException {
        List<Order> orders = null;

        Connection cn = null;
        try {
            cn = ConnectionPool.getConnection();
            MySqlDaoFactory df = (MySqlDaoFactory)DAOFactory.getDAOFactory("MYSQL");
            orders = df.getOrderDAO(cn).findAllForUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        int totalQuantityBought = quantityBought;
       for(Order order: orders){
           totalQuantityBought += order.getQuantity();
       }
        return totalQuantityBought >= REGULAR_LIMIT;
    }
}
