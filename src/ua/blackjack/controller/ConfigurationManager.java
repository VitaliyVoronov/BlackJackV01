package ua.blackjack.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;

/**
 * Created by vitaliy on 12/6/16.
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    private static final String FILENAME = "/media/vitaliy/storage/IdeaProjects/BlackJackV01/resources/blackjack";
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