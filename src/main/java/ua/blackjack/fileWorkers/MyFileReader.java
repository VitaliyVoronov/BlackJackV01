package ua.blackjack.fileWorkers;

import ua.blackjack.model.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MyFileReader {

    public MySettings getSettingsByNameFromXML(String playerName,String filePath){
        MySettings mySettings = null;
        List<MySettings> mySettingsList = getListSettingsFromXML(filePath);

        if (mySettingsList != null) {
            for (MySettings ms : mySettingsList) {
                if (ms.getName().equalsIgnoreCase(playerName)) {
                    mySettings = ms;
                    break;
                }
            }
            return mySettings;
        } else {
            return null;
        }
    }

    public List<MySettings> getListSettingsFromXML(String filePath) {
        List<MySettings> mySettingsList = new ArrayList<>();
        try {
            File inputFile = new File(filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            MyFileSaxParser myFileSaxParser = new MyFileSaxParser();
            saxParser.parse(inputFile, myFileSaxParser);
            mySettingsList = myFileSaxParser.getListSettingsFromXML();
        }catch (Exception e){
            System.out.println("Problem with: "+ this.getClass());
            e.printStackTrace();
        }

        return mySettingsList;
    }
}
