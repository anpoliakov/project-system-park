package models.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
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
        listConnections = new ArrayBlockingQueue<>(sizePool);

        userNameDB = confManager.getUserNameDB();
        passwordDB = confManager.getPasswordDB();
        driverDB = confManager.getDriverDB();
        urlDB = confManager.getUrlDB();
        sizePool = confManager.getSizePool();

        try {
            Class.forName(driverDB);
            fillPool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            /* Log4j */
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
            /* Log4j */
        }
        return connection;
    }

    private void fillPool(){
        for (int i = 0; i < sizePool; i++) {
            try {
                listConnections.put(createConnection());
            } catch (InterruptedException e) {
                e.printStackTrace();
                /* Log4j */
            }
        }
    }

    public Connection getConnection(){
        synchronized(ConnectionPool.class){
            Connection connect = null;
            try {
                connect = listConnections.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                /* Log4j */
            }
            return connect;
        }
    }

    public void returnConnection(Connection connection){
        try {
            listConnections.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
            /* Log4j */
        }
    }


}


