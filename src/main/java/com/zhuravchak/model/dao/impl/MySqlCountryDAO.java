package com.zhuravchak.model.dao.impl;

import com.zhuravchak.model.dao.abstr.CountryDAO;
import org.apache.log4j.Logger;
import java.sql.Connection;

/**
 * Created by Yaroslav on 25-Sep-17.
 */
public class MySqlCountryDAO extends CountryDAO {

    private final static Logger LOG = Logger.getLogger(MySqlCountryDAO.class);

    /**
     * Constructor
     */
    public MySqlCountryDAO(Connection connection) {
        super(connection);
    }
}
