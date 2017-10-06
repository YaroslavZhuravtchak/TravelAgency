package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.User;
import com.zhuravchak.domain.enums.UserRole;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserDAO.
 */
public abstract class UserDAO extends AbstractJDBCDao<User> {

    private final static Logger LOG = Logger.getLogger(UserDAO.class);

    /**
     * Constructor
     */
    public UserDAO(Connection connection) {
        super(connection);
    }

    private static final String SQL_SELECT_ENTITY_BY_ID = "select * from user";
    private static final String SQL_UPDATE_ENTITY = "update user set login = ?, password = ?, salt = ?, role = ?, firstname = ?," +
            "lastname = ?, phone_number = ?, email = ?,  isRegular = ? where id = ?";
    private static final String SQL_DELETE_ENTITY_BY_ID = "delete from user where id = ?";
    private static final String SQL_CREATE_ENTITY = "insert into user (login, password, salt, role, firstname, lastname," +
            " phone_number, email,  isRegular) values (?,?,?,?,?,?,?,?,?)";

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
    protected void prepareStatementForUpdate(PreparedStatement ps, User entity) throws DAOException {
        try {
            ps.setString(1, entity.getLogin());
            ps.setString(2,entity.getPassword());
            ps.setString(3, entity.getSalt());
            ps.setString(4, String.valueOf(entity.getRole()));
            ps.setString(5, entity.getFirstName());
            ps.setString(6,entity.getLastname());
            ps.setString(7,entity.getPhoneNumber());
            ps.setString(8, entity.getEmail());
            ps.setBoolean(9,entity.isRegular());
            ps.setLong(10, entity.getId());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    protected void prepareStatementForCreate(PreparedStatement ps, User entity) throws DAOException {
        try {
            ps.setString(1, entity.getLogin());
            ps.setString(2,entity.getPassword());
            ps.setString(3, entity.getSalt());
            ps.setString(4, String.valueOf(entity.getRole()));
            ps.setString(5, entity.getFirstName());
            ps.setString(6,entity.getLastname());
            ps.setString(7,entity.getPhoneNumber());
            ps.setString(8, entity.getEmail());
            ps.setBoolean(9,entity.isRegular());
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
    }

    /* (non-Javadoc)
        * @see com.zhuravchak.model.dao.abstr.AbstractJDBCDao
        */
    @Override
    public List<User> parseResultSet (ResultSet resultSet)  throws DAOException {
        List<User> users = new ArrayList<>();

        try {
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                user.setRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setRegular(resultSet.getBoolean("isRegular"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed): " + e,e);
        }
        return users;
    }

    /**
     * Find user by id.
     * @param  login the string
     * @return the user
     * @throws DAOException the DAO exception
     */
    public abstract User findEntityByLogin(String login) throws DAOException;
}