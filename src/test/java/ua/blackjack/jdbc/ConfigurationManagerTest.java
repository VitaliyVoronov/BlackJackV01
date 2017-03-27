package ua.blackjack.jdbc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/26/17
 */
public class ConfigurationManagerTest {
    @Test
    public void getInstance() throws Exception {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertNotNull(configurationManager);
    }

}