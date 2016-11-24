package ua.blackjack.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.blackjack.model.MySettings;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MyFileSaxParser extends DefaultHandler {

    String name;
    MySettings goalSettings;
    MySettings mset;
    String thisElement;

    public MyFileSaxParser(String name) {
        mset = new MySettings();
        this.name = name;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
            if (thisElement.equals("name")) {
                mset.setName(new String(ch, start, length));
            }
            if (thisElement.equals("decks")) {
                mset.setDecks(new Integer(new String(ch, start, length)));
            }
            if (thisElement.equals("minBet")) {
                mset.setMinBet(new Integer(new String(ch, start, length)));
            }
            if (thisElement.equals("maxBet")) {
                mset.setMaxBet(new Integer(new String(ch, start, length)));
            }
            if (thisElement.equals("money")) {
                mset.setMoney(new Integer(new String(ch, start, length)));
            }
        checkSettings();

    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    public void checkSettings(){
        if (thisElement.equals("player") && mset.getName() != null) {
                if (mset.getName().equals(name)) {
                    goalSettings = mset;
                    mset = new MySettings();
                }
        }
    }

    public MySettings getSettings(){
        //setName(name);
        return goalSettings;
    }

    public void setName(String name) {
        this.name = name;
    }
}
