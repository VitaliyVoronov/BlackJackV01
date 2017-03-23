package ua.blackjack.fileWorkers;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.blackjack.model.MySettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MyFileSaxParser extends DefaultHandler {

    final static Logger logger = Logger.getLogger(MyFileSaxParser.class);

    boolean bName = false;
    boolean bDecks = false;
    boolean bMinBet = false;
    boolean bMaxBet = false;
    boolean bMoney = false;

    private MySettings currentSettings = new MySettings();
    private List<MySettings> settingsList = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.trace("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("decks")) {
            bDecks = true;
        } else if (qName.equalsIgnoreCase("minBet")) {
            bMinBet = true;
        } else if (qName.equalsIgnoreCase("maxBet")) {
            bMaxBet = true;
        } else if (qName.equalsIgnoreCase("money")) {
            bMoney = true;
        }

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        if (qName.equals("player")) {
            try {
                settingsList.add(currentSettings.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (bName) {
            currentSettings.setName(new String(ch, start, length));
            bName = false;
        }
        if (bDecks) {
            currentSettings.setDecks(new Integer(new String(ch, start, length)));
            bDecks = false;
        }
        if (bMinBet) {
            currentSettings.setMinBet(new Integer(new String(ch, start, length)));
            bMinBet = false;
        }
        if (bMaxBet) {
            currentSettings.setMaxBet(new Integer(new String(ch, start, length)));
            bMaxBet = false;
        }
        if (bMoney) {
            currentSettings.setMoney(new Integer(new String(ch, start, length)));
            bMoney = false;
        }

    }

    @Override
    public void endDocument() {
        logger.trace("Stop parse XML...");
    }

    public List getListSettingsFromXML() {
        logger.trace("Return settings list from XML!");
        return settingsList;
    }
}
