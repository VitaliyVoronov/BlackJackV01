package ua.blackjack.jdbc;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to DB
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class JdbcConnector {

    final static Logger logger = Logger.getLogger(JdbcConnector.class);

    private Connection con;

    //Return connection to mysql db
    public Connection getConnection() {

        ConfigurationManager cfg = ConfigurationManager.getInstance();

        try {
            Class.forName(cfg.getDriver());
            logger.trace("Try to get connection.");
            con = DriverManager.getConnection(cfg.getUrl(), cfg.getUser(),
                    cfg.getPassword());
            logger.trace("Got connection.");
        } catch (ClassNotFoundException e) {
            logger.fatal("Can't load database driver.", e);

        } catch (SQLException e) {
            logger.fatal("Can't connect to database.", e);
        }
        if(con ==null) {
            logger.warn("Driver type is not correct in URL");
        }
        return con;
    }
    //Close connection
//    public boolean closeConnection() {
//        if (con != null) {
//            try {
//                logger.trace("Try to close connection.");
//                con.close();
//                logger.debug("Closed connection.");
//                return true;
//            } catch (SQLException e) {
//                logger.debug("Can not close connection!");
//                return false;
//            }
//        }
//        return false;
//    }

}
