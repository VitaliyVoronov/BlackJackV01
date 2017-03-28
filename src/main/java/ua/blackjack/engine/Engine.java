package ua.blackjack.engine;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.blackjack.fileWorkers.MyFileReader;
import ua.blackjack.fileWorkers.MyFileWriter;
import ua.blackjack.jdbc.PlayerDAOImpl;
import ua.blackjack.model.*;

import java.util.ArrayList;

/**
 * This is controller fo all program
 *
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class Engine {

    final static Logger logger = Logger.getLogger(Engine.class);

    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

//    private int decks;
//    private int maxBet;
//    private int minBet;
//    private int money;
    private int bet;
//    private PlayerDAOImpl playerDAO;
    private ArrayList<Card> shoes;
    private Player dealer;
    private Player player;
    private String massage;
    private boolean standPushed;
    private boolean dealPushed;
    private boolean continuePushed;
    private boolean isGame;
    private boolean isWin;
    private boolean isEnter;
    private String filePath;

    public Engine() {
//        decks = 1;
//        maxBet = 20;
//        minBet = 1;
//        money = 50;
//        playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
        continuePushed = true;
        isGame = false;
        massage = "";
        shoes = new ArrayList();
        dealer = (Player) ctx.getBean("dealer");
        filePath = getClass().getResource("/playerSettings.xml").getPath();
    }

    //Use this method for authorization
    public boolean signIn(String login, String password){
        PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
        player = playerDAO.getPlayerByNameAndPassword(login,password);
        if (player != null){
            return true;
        } else {
            return false;
        }
    }
    //Return false if login is busy and true is free
    public boolean isAvailableLogin(String login) {
        PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
        return playerDAO.isAvailableName(login);
    }
    //Service method for engine.Add new player to DB
    private boolean addPlayerToDB(String name, String password, String email) {
        PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
        if (playerDAO.addPlayerToDB(name, password, email)){
            return true;
        }
        return false;
    }
    //Add player to db and add default settings to xml by this player
    public boolean signUp(String login, String password, String email){
        if (addPlayerToDB(login,password,email)){
            player = new Player();
            player.setName(login);
            setDefaultSettings();
            saveNewSettingsToXML(player.getSettings());
            player = null;
            return true;
        }
        return false;

    }
    //TODO I need change this
    public void setDefaultSettings(){
        player.getSettings().setDecks(2);
        player.getSettings().setMaxBet(10);
        player.getSettings().setMinBet(5);
        player.getSettings().setMoney(100);
    }
    //Set settings from xml by login to player's settings
    public void getSettingsFromXml(String playerName) {
        MyFileReader myFileReader = new MyFileReader();
        MySettings mySettings = myFileReader.getSettingsByNameFromXML(playerName,filePath);
        if (mySettings != null){
            player.setSettings(mySettings);
        } else {
            setDefaultSettings();
            saveNewSettingsToXML(player.getSettings());
//            mySettings = myFileReader.getSettingsByNameFromXML(playerName,filePath);
            logger.debug("No player's settings for "+ playerName);
        }
    }
    //When player set new settings its change in player and save in xml file
    public boolean changeSettings(int decks, int minBet, int maxBet, int money){
        player.getSettings().setDecks(decks);
        player.getSettings().setMinBet(minBet);
        player.getSettings().setMaxBet(maxBet);
        player.getSettings().setMoney(money);
        saveNewSettingsToXML(player.getSettings());
        return false;
    }
    //Service method for engine. This method save new settings to xml and return true or false.
    private boolean saveNewSettingsToXML(MySettings newSettings){
        MyFileWriter myFileWriter = new MyFileWriter();
        if(myFileWriter.writeSettingsToFile(newSettings, filePath)){
            return true;
        } else {
            return false;
        }
    }
    //If player sign in isEnter have to return true else false.
    public boolean isEnter(){
        return isEnter;
    }
    //Method run when player pushed new game button
    public void startNewGame(){
        getSettingsFromXml(player.getName());
        //TODO Temp design
        player.setMoney(player.getSettings().getMoney());
        createDecksAndAddToShoes(player.getSettings().getDecks());
        mixShoes();
        player.clearHand();
        dealer.clearHand();
    }
    //Service method for engine. Name method tell all
    private void createDecksAndAddToShoes(int num) {
        for (int i = 0; i < num; i++) {
            ArrayList<Card> list = new CardDeck().getCardDeck();
            for (int j = 0; j < list.size(); j++) {
                shoes.add(list.get(j));
            }
        }
    }
    //Service method for engine.Mix cards in shoes
    private void mixShoes() {
        ArrayList<Card> tempList = new ArrayList<>();
        for (int i = 0; i < shoes.size(); i++) {
            int rand = (int) (Math.random() * shoes.size());
            tempList.add(shoes.get(rand));
            shoes.remove(rand);
            i = 0;
        }
        tempList.add(shoes.get(0));
        shoes.remove(0);
        shoes = tempList;
    }

    public void deal(){
        if (isGame){
            dealOneCardToPlayer();
        } else {
            firstDealToAll();
            isGame = true;
        }
        oneStep();
    }

    public void firstDealToAll() {
        if (bet >= player.getSettings().getMinBet()) {
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
        } else if (bet < player.getSettings().getMinBet()) {
            massage = "Your Bet is to small! Min bet is: " + player.getSettings().getMinBet();
        } else if (bet > player.getSettings().getMaxBet()) {
            massage = "Your Bet is to big! Max bet is: " + player.getSettings().getMaxBet();
        }
        oneStep();
    }

    public void dealOneCardToPlayer() {
        if (isGame) {
            player.addCardToHand(dealOneCard());
        }
        oneStep();
    }

    private Card dealOneCard() {
        Card card = shoes.get(0);
        shoes.remove(0);
        return card;
    }

    public void oneStep() {
        checkSumPlayerAndDealer();
    }

//    ________________________________________________________________

    //TODO Wrong way, it is should be service method for engine
    public void takeMoneyFromPlayerToBet(int moneyToBet) {
        if (moneyToBet > player.getMoney()) {
            massage = "You have not enough money!";
        } else if ((moneyToBet + bet) > player.getSettings().getMaxBet()) {
            massage = "Max bet: " + player.getSettings().getMaxBet();
        } else {
            setBet(player.takeMoney(moneyToBet));
        }
    }

    public ArrayList<Card> getShoes() {
        return shoes;
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void dealCardsToDealer() {
        while (true) {
            if (dealer.getSumNumbers() < 17) {
                dealer.addCardToHand(dealOneCard());
                oneStep();
            } else if (dealer.getSumNumbers() >= 17) {
                break;
            }
            oneStep();
        }
        oneStep();

    }

    public void checkSumPlayerAndDealer() {
        if (player.getSumNumbers() > 21) {
            isGame = false;
            massage = "Game over. You lose!";
            isWin = false;
        }
        if (dealer.getSumNumbers() > 21 && player.getSumNumbers() <= 21) {
            isGame = false;
            massage = "Game over. You won!";
            isWin = true;
        }
        if (player.getSumNumbers() == 21) {
            isGame = false;
            massage = "Game over. You win! BlackJack";
            isWin = true;
        }

        if (dealer.getSumNumbers() == 21) {
            isGame = false;
            massage = "Game over. You lose!";
            isWin = false;
        }

        if (player.getSumNumbers() > dealer.getSumNumbers() && player.getSumNumbers() <= 21 && !isGame) {
//            isGame = false;
            massage = "Game over. You won!";
            isWin = true;
        }

    }

    public String getMassage() {
        return massage;
    }

    public void setBet(int bet) {
        this.bet += bet;
    }

    public int getBet() {
        return bet;
    }

    public void setGameTrue() {
        isGame = true;
    }

    public void setGameFalse() {
        isGame = false;
    }

    public boolean isGame() {
        return isGame;
    }


    public boolean isDealPushed() {
        return dealPushed;
    }


    public boolean isContinuePushed() {
        return continuePushed;
    }

    public void setContinuePushed(boolean continuePushed) {
        this.continuePushed = continuePushed;
    }

    public void clearTable() {
        player.getHand().getCards().clear();
        dealer.getHand().getCards().clear();
    }

    public void countWin() {
        int playerMoney = player.getMoney();
        if (isWin) {
            playerMoney += bet * 2;
            player.setMoney(playerMoney);
            clearBet();
        }
    }

    public void clearBet() {
        bet = 0;
    }

    public void setEnter(boolean enter) {
        isEnter = enter;
    }

    public void clearPoints(){
        player.clearSumNumbers();
        dealer.clearSumNumbers();
    }

}