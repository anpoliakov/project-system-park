package model.util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    final static Logger logger = Logger.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private ConfigurationManager confManager;
    
    private BlockingQueue <Connection> listConnections;

    private String userNameDB;
    private String passwordDB;
    private String driverDB;
    private String urlDB;
    private int sizePool;

    private ConnectionPool() {
        confManager = ConfigurationManager.getInstance();
        userNameDB = confManager.getUserNameDB();
        passwordDB = confManager.getPasswordDB();
        driverDB = confManager.getDriverDB();
        urlDB = confManager.getUrlDB();
        sizePool = confManager.getSizePool();
        listConnections = new ArrayBlockingQueue<Connection>(sizePool);
        try {
            Class.forName(driverDB);
            fillPool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("Failed to load the class in ClassLoader", e);
        }
    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            synchronized(ConnectionPool.class){
                if(instance == null){
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(urlDB, userNameDB, passwordDB);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Not access to data base", e);
        }
        return connection;
    }

    private void fillPool(){
        for (int i = 0; i < sizePool; i++) {
            try {
                listConnections.put(createConnection());
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("Failed to insert a connection in the connection list", e);
            }
        }
    }

    public Connection getConnection(){
        synchronized(ConnectionPool.class){
            Connection connect = null;
            try {
                connect = listConnections.take();
                System.out.println("INFO (ConnectionPool, 77 line): Connection is obtained > ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("Failed to get a connection from list connections", e);
            }
            return connect;
        }
    }

    public void returnConnection(Connection connection){
        if(connection != null){
            try {
                listConnections.put(connection);
                System.out.println("INFO (ConnectionPool, 90 line): Connection returned < ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("Failed to return the connection to the list", e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


//    public static void closeStatement(Statement st) {
//        if (st != null) {
//            try {
//                st.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }