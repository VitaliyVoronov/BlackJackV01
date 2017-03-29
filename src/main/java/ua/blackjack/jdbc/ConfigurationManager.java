package ua.blackjack.jdbc;

import org.apache.log4j.Logger;
import ua.blackjack.fileWorkers.MyFileReader;

import java.io.*;
import java.util.PropertyResourceBundle;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */
public class ConfigurationManager {

    final static Logger logger = Logger.getLogger(ConfigurationManager.class);

    private static ConfigurationManager instance;
    private final String FILENAME = getClass().getResource("/db.properties").getPath();
    private FileInputStream fis;
    private PropertyResourceBundle resource;
    private String url;
    private String driver;
    private String user;
    private String password;

    private ConfigurationManager(){
        doConfiguration();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private void doConfiguration(){
        try {
            logger.trace("Try to create FileInputStream to db.properties");
            fis = new FileInputStream(FILENAME);
        } catch (FileNotFoundException e) {
            logger.error("Failed to create FileInputStream to db.properties",e);
        }
        try {
            logger.trace("Try to create PropertyResourceBundle with FileInputStream to db.properties");
            resource = new PropertyResourceBundle(fis);
        } catch (IOException e) {
            logger.trace("Failed to create PropertyResourceBundle with FileInputStream to db.properties",e);
        }
        url = resource.getString("url");
        driver = resource.getString("driver");
        user = resource.getString("user");
        password = resource.getString("password");
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
