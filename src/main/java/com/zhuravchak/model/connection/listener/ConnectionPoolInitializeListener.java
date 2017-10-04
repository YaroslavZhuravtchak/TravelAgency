package com.zhuravchak.model.connection.listener;

import com.zhuravchak.model.connection.ConnectionPool;
import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The Class ConnectionPoolInitializeListener.
 */
public class ConnectionPoolInitializeListener implements ServletContextListener {

    private final static Logger LOG = Logger.getLogger(ConnectionPoolInitializeListener.class);

    /** The connection connection. */
    public static ConnectionPool connectionPool;

      /* (non-Javadoc)
       * @see javax.maincontroler.ServletContextListener
       */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.init();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

     /* (non-Javadoc)
      * @see javax.maincontroler.ServletContextListener
      */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.destroy();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
