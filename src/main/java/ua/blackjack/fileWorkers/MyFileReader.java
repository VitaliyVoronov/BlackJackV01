package ua.blackjack.fileWorkers;

import org.apache.log4j.Logger;
import ua.blackjack.model.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read through saxparser settings from xml file
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class MyFileReader {

    final static Logger logger = Logger.getLogger(MyFileReader.class);

    //Take player name and path to file and return settings by name from this file
    public MySettings getSettingsByNameFromXML(String playerName,String filePath){
        MySettings mySettings = null;
        List<MySettings> mySettingsList = getListSettingsFromXML(filePath);

        if (mySettingsList != null) {
            logger.trace("Try fined settings from file: "+filePath+"by name");
            for (MySettings ms : mySettingsList) {
                if (ms.getName().equalsIgnoreCase(playerName)) {
                    mySettings = ms;
                    break;
                }
                logger.info("Maybe settings not found! Check it in th file: "+filePath);
            }
            logger.trace("Method getSettingsByNameFromXML return mySettings: "+mySettings.getName());
            return mySettings;
        } else {
            logger.warn("Method getSettingsByNameFromXML return null");
            return null;
        }
    }
    //Take path to xml file and return list with all settings from this file
    public List<MySettings> getListSettingsFromXML(String filePath) {
        List<MySettings> mySettingsList = new ArrayList<>();
        try {
            logger.trace("Try to read file: "+ filePath);
            File inputFile = new File(filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MyFileSaxParser myFileSaxParser = new MyFileSaxParser();
            saxParser.parse(inputFile, myFileSaxParser);
            mySettingsList = myFileSaxParser.getListSettingsFromXML();
        }catch (Exception e) {
            logger.info("Problem with file: " + filePath, e);
        }
        logger.trace("Method getListSettingsFromXML returned list by settings!");
        return mySettingsList;
    }
}
