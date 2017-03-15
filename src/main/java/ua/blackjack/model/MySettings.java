package ua.blackjack.model;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MySettings {

    private String name;
    private int decks;
    private int minBet;
    private int maxBet;
    private int money;

    public void setName(String name) {
        this.name = name;
    }

    public void setDecks(int decks) {
        this.decks = decks;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    public void setMaxBet(int maxBet) {
        this.maxBet = maxBet;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getDecks() {
        return decks;
    }

    public int getMinBet() {
        return minBet;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public int getMoney() {
        return money;
    }

    public String getStringSettingsForXml(){
        return "<player>\n<name>"+name+"</name>\n<decks>"+decks+"</decks>\n<minBet>"+minBet+"</minBet>\n" +
                "<maxBet>"+maxBet+"</maxBet>\n<money>"+money+"</money>\n</player>";
    }

    public String getInfoSettings(){
        return "Name: "+name+"\nDecks: "+decks+"\nMin bet: "+minBet+"\nMax bet: "+maxBet+"\nMax bet: "+maxBet+"\nMoney: "+money;
    }
}
