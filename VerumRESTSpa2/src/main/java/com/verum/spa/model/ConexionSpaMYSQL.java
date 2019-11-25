package com.verum.spa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSpaMYSQL {

    private String DRIVER;
    private String PATH;
    private String USER;
    private String PASS;
    private Connection connection;

    public ConexionSpaMYSQL() {
        this.DRIVER = "com.mysql.jdbc.Driver";
        this.PATH = "jdbc:mysql://localhost:3306/myspa?autoReconnect=true&useSSL=false";
        this.USER = "root";
        this.PASS = "root";
    }

    public String getDRIVER() {
        return DRIVER;
    }

    public String getPATH() {
        return PATH;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASS() {
        return PASS;
    }

    public Connection startConnection() throws SQLException {
        if (connection != null) {
            return connection;
        } else {
            connection = DriverManager.getConnection(PATH, USER, PASS);
            return connection;
        }
    }
    
    public Connection startConnection2() throws SQLException {
        connection = DriverManager.getConnection(PATH, USER, PASS);
       return  connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
