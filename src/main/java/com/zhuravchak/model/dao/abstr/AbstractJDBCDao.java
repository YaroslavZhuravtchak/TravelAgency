package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Entity;
import com.zhuravchak.model.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * The Class GenericDAO.
 *
 * @param <T> the generic type
 */
public abstract class AbstractJDBCDao<T extends Entity> implements GenericDAO<T> {

    private final static Logger LOG = Logger.getLogger(AbstractJDBCDao.class);

    protected Connection connection;

    /**
     * Constructor
     */
    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Find select query string
     *
     * @return the string
     */
    public abstract String getSelectQuery();

    /**
     * Find update query string
     *
     * @return the string
     */
    public abstract String getUpdateQuery();

    /**
     * Find delete query string
     *
     * @return the string
     */
    public abstract String getDeleteQuery();

    /**
     * Find create query string
     *
     * @return the string
     */
    public abstract String getCreateQuery();

    /**
     * Find list of entities.
     *
     * @param rs ResultSet
     * @return the list
     * @throws DAOException the DAO exception
     */
    protected abstract List<T> parseResultSet(ResultSet rs)  throws DAOException;

    /**
     * Prepare PreparedStatement for update
     *
     * @param ps PreparedStatement
     * @param entity the generic type
     * @return void
     * @throws DAOException the DAO exception
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement ps, T entity)  throws DAOException;

    /**
     * Prepare PreparedStatement for create
     *
     * @param ps PreparedStatement
     * @param entity the generic type
     * @return void
     * @throws DAOException the DAO exception
     */
    protected abstract void prepareStatementForCreate(PreparedStatement ps, T entity)  throws DAOException;

    /* (non-Javadoc)
     * @see com.zhuravchak.model.dao.abstr.GenericDAO
     */
    @Override
    public T findEntityById(Long id) throws DAOException {

        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException("findEntityById", e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }

    /* (non-Javadoc)
    * @see com.zhuravchak.model.dao.abstr.GenericDAO
    */
    @Override
    public List<T> findAll() throws DAOException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException("findAll: ",e);
        }
        return list;
    }

    /* (non-Javadoc)
     * @see com.zhuravchak.model.dao.abstr.GenericDAO
     */
    @Override
    public boolean update(T entity) throws DAOException  {
        String sql = getUpdateQuery();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, entity);
            count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException ("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DAOException ("update: ",e);
        }
        return count == 1;
    }

    /* (non-Javadoc)
         * @see com.zhuravchak.model.dao.abstr.GenericDAO
         */
    @Override
    public boolean delete(Long id) throws DAOException {
        String sql = getDeleteQuery();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new DAOException("delete: ", e);
        }
        return count == 1;
    }

    /* (non-Javadoc)
     * @see com.zhuravchak.model.dao.abstr.GenericDAO
     */
    @Override
    public boolean create(T object) throws DAOException {

        int count = 0;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForCreate(statement, object);
            count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            LOG.error(e+"(create)");
            throw new DAOException("create: ",e);
        }

        return count == 1;
    }
    /* (non-Javadoc)
         * @see java.lang.AutoCloseable
         */
    @Override
    public void close() throws Exception {
        ConnectionPool.closeConnection(connection);
    }
}
