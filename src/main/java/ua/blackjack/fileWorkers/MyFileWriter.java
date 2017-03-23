package ua.blackjack.fileWorkers;

import org.apache.log4j.Logger;
import ua.blackjack.model.MySettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MyFileWriter {

    final static Logger logger = Logger.getLogger(MyFileWriter.class);

    public void writeSettingsToFile(MySettings myNewSettings, String filePath){
        MyFileReader myFileReader = new MyFileReader();
        List<MySettings> mySettingsList = myFileReader.getListSettingsFromXML(filePath);

        boolean isChanged = false;
        for (int i = 0; i < mySettingsList.size(); i++){
            if (mySettingsList.get(i).getName().equalsIgnoreCase(myNewSettings.getName())){
                mySettingsList.get(i).setDecks(myNewSettings.getDecks());
                mySettingsList.get(i).setMinBet(myNewSettings.getMinBet());
                mySettingsList.get(i).setMaxBet(myNewSettings.getMaxBet());
                mySettingsList.get(i).setMoney(myNewSettings.getMoney());
                isChanged = true;
                logger.trace("Add changed settings by "+myNewSettings.getName()+" player!");
            }
            logger.trace("Not found settings for "+myNewSettings.getName()+" player!");
        }

        if (!isChanged){
            mySettingsList.add(myNewSettings);
            logger.trace("Added to list new settings by "+myNewSettings.getName()+"player!");
        }
        logger.trace("Try to write list settings to file: "+filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            String startString = "<players>\n";
            String endString = "</players>";

            bw.write(startString);
            for (MySettings mySettings : mySettingsList ) {
                bw.write(mySettings.getStringSettingsForXml()+"\n");
            }
            bw.write(endString);

        } catch (IOException e) {
            logger.warn("Problem to write list settings to file: "+filePath,e);

        }


    }
    }

