package com.dmitri.ifmo_web_lab_2.database;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ManagedBean(eager = true)
@ApplicationScoped
public class DatabaseConnector {

    private static final String HOST = System.getenv("DB_HOST");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String USER = "postgres"; //System.getenv("DB_USER");
    private static final String PASSWORD = "postgres"; //System.getenv("DB_PASSWORD");
    private static final String URL = "jdbc:postgresql://" + "127.0.0.1" + ":5432/" + "postgres";

    public DatabaseConnector() {}

    public Connection getConnection() throws SQLException {
        System.out.println(USER);
        System.out.println(PASSWORD);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
