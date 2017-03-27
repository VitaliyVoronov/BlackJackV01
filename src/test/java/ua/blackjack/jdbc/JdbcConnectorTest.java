package ua.blackjack.jdbc;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/26/17
 */
public class JdbcConnectorTest {
    @Test
    public void getConnection() throws Exception {
        JdbcConnector jdbcConnector = new JdbcConnector();
        Connection con = jdbcConnector.getConnection();
        assertNotNull(con);
        assertTrue(con.isValid(0));
        con.close();

//        assertNotNull(jdbcConnector.getConnection());
//        jdbcConnector.closeConnection();
    }

}