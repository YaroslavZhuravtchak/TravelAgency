package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.dao.abstr.UserDAO;
import com.zhuravchak.domain.User;
import com.zhuravchak.model.exception.UserDAOException;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Yaroslav on 26-Sep-17.
 */
public class MySqlUserDAO extends UserDAO {

    private final static Logger LOG = Logger.getLogger(MySqlUserDAO.class);

    static final String SQL_SELECT_ENTITY_BY_LOGIN = "select * from user where login = ?";

    /**
     * Constructor
     */
    public MySqlUserDAO(Connection connection) {
        super(connection);
    }

    /**
     * Find user by id.
     * @param  login the string
     * @return the user
     * @throws UserDAOException
     */
    public User findEntityByLogin(String login) throws UserDAOException {
        List<User> list;
        String sql = getSelectQuery();
        sql += " WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new UserDAOException(" findEntityByLogin ", e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new UserDAOException(" Received more than one record(findEntityByLogin) ");
        }
        return list.iterator().next();
    }
}
