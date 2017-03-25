package ua.blackjack.jdbc;

import java.io.*;
import java.util.PropertyResourceBundle;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    private final String FILENAME = getClass().getResource("/db.properties").getPath();
    private FileInputStream fis;
    private PropertyResourceBundle resource;
    private String url;
    private String driver;
    private String user;
    private String password;

    private ConfigurationManager(){
        try {
            fis = new FileInputStream(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            resource = new PropertyResourceBundle(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = resource.getString("url");
        driver = resource.getString("driver");
        user = resource.getString("user");
        password = resource.getString("password");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
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
