package ua.blackjack.engine;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.blackjack.fileWorkers.MyFileReader;
import ua.blackjack.fileWorkers.MyFileWriter;
import ua.blackjack.jdbc.PlayerDAOImpl;
import ua.blackjack.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is controller for all program
 *
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class Engine {

    final static Logger logger = Logger.getLogger(Engine.class);

    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    private int bet;
    private List<Card> shoes;
    private Player dealer;
    private Player player;
    private String message;
    private boolean standPushed;
    private boolean dealPushed;
    private boolean newGamePushed;
    private boolean isGame;
    private boolean isWin;
    private boolean isSignIn;
    private String filePath;

    public Engine() {
        newGamePushed = true;
        isGame = false;
        message = "";
        shoes = new ArrayList();
        dealer = (Player) ctx.getBean("dealer");
        filePath = getClass().getResource("/playerSettings.xml").getPath();
    }

    //TODO new game if isGame = false
    public boolean newGame(){
        if (!isGame) {
            setNewGamePushed(true);
            clearTable();
            countWin();
            clearBet();
            clearPoints();
            setGameFalse();
            setMessage("Make your bet!");
            return true;
        } else {
            setMessage("End current game!");
            return false;
        }
    }

    public boolean hit(){
        if (dealOneCardToPlayer()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean stand(){
        if (isGame()) {
            setGameFalse();
            dealCardsToDealer();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Use when player push deal button
     * @return true if was start new game
     */
    public boolean deal(){
        if (isNewGamePushed()){
            firstDealToAll();
            if (player.getHand().getCards().size() > 0 && dealer.getHand().getCards().size() > 0) {
                setNewGamePushed(false);
                setGameTrue();
            }
            return true;
        } else {
            setMessage("Deal cards when start new game!");
            return false;
        }
    }

    //    public void deal(){
//        if (isGame){
//            dealOneCardToPlayer();
//        } else {
//            firstDealToAll();
//            isGame = true;
//        }
//        oneStep();
//    }

    /**
     * Use this method for authorization
     * @param login
     * @param password
     * @return true if sign in and false if not sign in
     */
    public boolean signIn(String login, String password){
        PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
        player = playerDAO.getPlayerByNameAndPassword(login,password);
        if (player != null){
            getSettingsFromXmlAndSetItToPlayer(login);
            setSignIn(true);
            return true;
        } else {
            setMessage("Incorrect login or password!");
            return false;
        }
    }

    /**
     * Check if login is free
     * @param login
     * @return Return false if login is busy and true is free
     */
    public boolean isAvailableLogin(String login) {
        if (login.length() > 0 && login != null) {
            PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
            return playerDAO.isAvailableName(login);
        } else {
            return false;
        }
    }

    /**
     * Service method for engine. Add new player to DB.
     * @param name
     * @param password
     * @param email
     * @return return true if player added to DB
     */
    private boolean addPlayerToDB(String name, String password, String email) {
        if (name != null || password != null || email != null){
            PlayerDAOImpl playerDAO = (PlayerDAOImpl) ctx.getBean("playerDAO");
            if (playerDAO.addPlayerToDB(name, password, email)){
                return true;
            }
        }
        return false;
    }

    /**
     * Registration method
     * Add player to db and add default settings to xml by this player.
     * @param login
     * @param password
     * @param email
     * @return true if all OK and false if something wrong
     */
    public boolean signUp(String login, String password, String email){
        if(!isAvailableLogin(login)){
            setMessage("This login is busy!");
            logger.debug("Login is busy!");
            return false;
        }
        logger.trace("Try to add new player to DB!");
        if (addPlayerToDB(login.trim(),password,email)){
            player = new Player();
            player.setName(login);
            setDefaultSettings();
            saveNewSettingsToXML(player.getSettings());
            player = null;
            setMessage("Registration completed successfully!");
            return true;
        }
        logger.debug("Fail to add new player to DB!");
        return false;

    }
    //TODO I need change this
    public boolean setDefaultSettings(){
        if (player != null) {
            player.getSettings().setDecks(2);
            player.getSettings().setMinBet(5);
            player.getSettings().setMaxBet(10);
            player.getSettings().setMoney(100);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set settings from xml by login to player's settings
     * @param playerName
     * @return true if all OK and false if something wrong
     */
    public boolean getSettingsFromXmlAndSetItToPlayer(String playerName) {
        if (playerName != null && playerName.length() > 0) {
            MyFileReader myFileReader = new MyFileReader();
            MySettings mySettings = myFileReader.getSettingsByNameFromXML(playerName, filePath);
            if (mySettings != null) {
                player.setSettings(mySettings);
                return true;
            } else {
                if (setDefaultSettings()) {
                    saveNewSettingsToXML(player.getSettings());
                    logger.debug("No player's settings for " + playerName);
                    return true;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * When player set new settings its change in player and save in xml file
     * @param settings
     * @return true if all OK and false if something wrong
     */
    public boolean changeSettings(MySettings settings){
        if (player != null) {
            player.getSettings().setDecks(settings.getDecks());
            player.getSettings().setMinBet(settings.getMinBet());
            player.getSettings().setMaxBet(settings.getMaxBet());
            player.getSettings().setMoney(settings.getMoney());
            saveNewSettingsToXML(player.getSettings());
            setMessage("Settings changed successful!");
            return true;
        }
        return false;
    }

    /**
     * Service method for engine. This method save new settings to xml and return true or false.
     * @param newSettings
     * @return true if all OK and false if something wrong
     */
    private boolean saveNewSettingsToXML(MySettings newSettings){
        MyFileWriter myFileWriter = new MyFileWriter();
        if(myFileWriter.writeSettingsToFile(newSettings, filePath)){
            return true;
        } else {
            return false;
        }
    }
    //If player sign in isSignIn have to return true else false.
    public boolean isSignIn(){
        return isSignIn;
    }

    /**
     * Method run when player pushed new game button
     */
    public void startGame(){
        getSettingsFromXmlAndSetItToPlayer(player.getName());
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

    public boolean firstDealToAll() {
        if (bet >= player.getSettings().getMinBet()) {
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
            oneStep();
            return true;
        } else if (bet < player.getSettings().getMinBet()) {
            setMessage("Your Bet is to small! Min bet is: " + player.getSettings().getMinBet());
            return false;
        } else if (bet > player.getSettings().getMaxBet()) {
            setMessage("Your Bet is to big! Max bet is: " + player.getSettings().getMaxBet());
            return false;
        }
        return false;
    }

    public boolean dealOneCardToPlayer() {
        if (isGame) {
            player.addCardToHand(dealOneCard());
            oneStep();
            return true;
        } else {
            return false;
        }

    }

    private Card dealOneCard() {
        Card card = shoes.get(0);
        shoes.remove(0);
        return card;
    }

    public void oneStep() {
        checkSumPlayerAndDealer();
    }

    public void bet(int moneyToBet){
        takeMoneyFromPlayerToBet(moneyToBet);
    }

//    ________________________________________________________________

    //TODO Wrong way, it is should be service method for engine
    public void takeMoneyFromPlayerToBet(int moneyToBet) {
        if (moneyToBet > player.getMoney()) {
            message = "You have not enough money!";
        } else if ((moneyToBet + bet) > player.getSettings().getMaxBet()) {
            message = "Max bet: " + player.getSettings().getMaxBet();
        } else {
            setBet(player.takeMoney(moneyToBet));
        }
    }

    public List<Card> getShoes() {
        return shoes;
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Use only for tests
     * @param player
     */
    public void setPlayer(Player player){
        this.player = player;
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
            message = "Game over. You lose!";
            isWin = false;
        }
        if (dealer.getSumNumbers() > 21 && player.getSumNumbers() <= 21) {
            isGame = false;
            message = "Game over. You won!";
            isWin = true;
        }
        if (player.getSumNumbers() == 21) {
            isGame = false;
            message = "Game over. You win! BlackJack";
            isWin = true;
        }

        if (dealer.getSumNumbers() == 21) {
            isGame = false;
            message = "Game over. You lose!";
            isWin = false;
        }

        if (player.getSumNumbers() > dealer.getSumNumbers() && player.getSumNumbers() <= 21 && !isGame) {
//            isGame = false;
            message = "Game over. You won!";
            isWin = true;
        }
    }

    public String getMessage() {
        return message;
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

    public boolean isNewGamePushed() {
        return newGamePushed;
    }

    public void setNewGamePushed(boolean newGamePushed) {
        this.newGamePushed = newGamePushed;
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

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public void clearPoints(){
        player.clearSumNumbers();
        dealer.clearSumNumbers();
    }

    public void setMessage(String str){
        message = str;
    }

}