package com.zhuravchak.model.connection;

import com.zhuravchak.model.exception.ConnectionPoolException;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

    /**
     * The Constant LOG.
     */
    private final static Logger LOG = Logger.getLogger(ConnectionPool.class);

    private static final String KEY_RESOURCE_PATH = "src/main/resources/database.properties";
    private static final String KEY_DRIVER_NAME = "driver-name";
    private static final String KEY_CONNECTION_STRING = "connection-string";
    private static final String POOL_SIZE = "pool-size";
    private static ConnectionPool HOLDER_INSTANCE;
    private ArrayBlockingQueue<Connection> connectionQueue;

    /**
     * Instantiates a new connection connection.
     *
     * @throws ConnectionPoolException the connection connection exception
     */
    private ConnectionPool() throws ConnectionPoolException {
        try {
            makeConnection();
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Failed to register driver.", e);
        } catch (IOException e) {
            throw new ConnectionPoolException("Failed to read database properties.", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Failed to get connection.", e);
        }
    }

    /**
     * Nested class ConnectionPoolHolder.
     */
    public static ConnectionPool getInstance() {
        return HOLDER_INSTANCE;
    }

    /**
     * Init connection.
     */
    public static void init() throws ConnectionPoolException {
        HOLDER_INSTANCE = new ConnectionPool() ;
        LOG.info("Creating a instance of  ConnectionPool");
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     * @throws ConnectionPoolException the connection connection exception
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = getInstance().connectionQueue.take();
        } catch (InterruptedException e) {
            LOG.error("Failed to take connection from connection.", e);
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Close connection.
     *
     * @param conn the connection
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                getInstance().connectionQueue.put(conn);
            } catch (InterruptedException e) {
                LOG.error("Failed to put connection to connection.", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * Make connections.
     *
     * @throws IOException the IO exception
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException the SQL exception
     */
    private void makeConnection() throws IOException, ClassNotFoundException, SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        Class.forName(resource.getString(KEY_DRIVER_NAME));
        int poolSize = Integer.valueOf(resource.getString(POOL_SIZE));
        connectionQueue = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(
                    resource.getString(KEY_CONNECTION_STRING),
                    resource.getString("user"),
                    resource.getString("password"));

            connectionQueue.offer(connection);
        }
        LOG.info("Connections was successfully created.");
    }

    /**
     * Close all connections.
     *
     * @throws ConnectionPoolException the connection connection exception
     */
    public static void destroy() throws ConnectionPoolException{
        int count = 0;
        for (Connection connection : getInstance().connectionQueue) {
            try {
                connection.close();
                count++;
            } catch (SQLException e) {
                throw new ConnectionPoolException("Failed to close connection.", e);
            }
        }
        LOG.info("Connections in the amount of " + count + " pieces successfully closed.");
    }
}