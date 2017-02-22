package ua.blackjack.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vitaliy on 12/6/16.
 */
public class JdbcConnector {

    private Connection conn;

    public Connection getConnection() {
        ConfigurationManager cfg =ConfigurationManager.getInstance();
        try {
            Class.forName(cfg.getDriver());
            conn = DriverManager.getConnection(cfg.getUrl(), cfg.getUser(),
                    cfg.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can't load database driver.");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't connect to database.");
        }
        if(conn==null) {
            System.out.println("Driver type is not correct in URL");
        }

        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                    conn.close();
            } catch (SQLException e) {
            }
        }

    }

}
