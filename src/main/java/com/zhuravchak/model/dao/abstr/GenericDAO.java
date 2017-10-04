package com.zhuravchak.model.dao.abstr;

import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.domain.Entity;
import java.util.List;

/**
 * The Interface GenericDAO.
 *
 * @param <T> the generic type
 */
public interface GenericDAO<T extends Entity> extends  AutoCloseable {

    /**
     * Find list of entities by id.
     *
     * @return the t
     * @throws DAOException the DAO exception
     */
     List<T> findAll() throws DAOException;

     /**
      * Find domain by id.
      *
      * @param id the id
      * @return the t
      * @throws DAOException the DAO exception
      */
     T findEntityById(Long id) throws DAOException;

     /**
      * Delete.
      *
      * @param id the id
      * @return true, if successful
      * @throws DAOException the DAO exception
      */
     boolean delete(Long id) throws DAOException;

     /**
      * Creates the.
      *
      * @param entity the domain
      * @return true, if successful
      * @throws DAOException the DAO exception
      */
     boolean create(T entity) throws DAOException;

     /**
      * Update.
      *
      * @param entity the domain
      * @return true, if successful
      * @throws DAOException the DAO exception
      */
     boolean update(T entity) throws DAOException;
}
