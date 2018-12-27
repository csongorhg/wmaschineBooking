/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.db_transactions;

/**
 *
 * @author zalchege
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectSingleton extends Thread {

    private String username, password, driver, db_url;
    private static Connection connection = null;
    private static Statement statement = null;
    private static DBConnectSingleton dBConnectSingleton;

    private DBConnectSingleton() {
    }

    public static DBConnectSingleton getInstance() {
        if (dBConnectSingleton == null) {
            dBConnectSingleton = new DBConnectSingleton();
        }
        return dBConnectSingleton;
    }

    @Override
    public void run() {
        try {
            connect();
        }  catch (Exception ex) {
            Logger.getLogger(DBConnectSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws SQLException, Exception {
        Class.forName(driver); // Saves on surrounding with try catch blocks but ensures the driver is there prior to connecting
        System.out.println("Connecting...");
        connection = DriverManager.getConnection(db_url, username, password);
        statement = connection.createStatement();
        System.out.println("Connected to database");
    }

    public static void disconnect() throws SQLException, Exception {
        statement.close();
        connection.close();
        System.out.println("Disconnected from database");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDb_url() {
        return db_url;
    }

    public void setDb_url(String db_url) {
        this.db_url = db_url;
    }

    public static Connection getConnection() {
        return connection;
    }
    
    public static Statement getStatement() {
        return statement;
    }
    

}
