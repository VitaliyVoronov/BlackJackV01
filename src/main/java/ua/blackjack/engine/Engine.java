package ua.blackjack.engine;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.blackjack.fileWorkers.MyFileReader;
import ua.blackjack.fileWorkers.MyFileWriter;
import ua.blackjack.jdbc.PlayerDAOImpl;
import ua.blackjack.model.Card;
import ua.blackjack.model.CardDeck;
import ua.blackjack.model.MySettings;
import ua.blackjack.model.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * It's engine for blakjack game
 * 
 * @author vitaliy
 * @version 1.0
 */

public class Engine {

    final static Logger logger = Logger.getLogger(Engine.class);

    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    private int decks;
    private int maxBet;
    private int minBet;
    private int money;
    private int bet;
    private PlayerDAOImpl connect;
    private ArrayList<Card> shoes = null;
    private Player dealer;
    private Player player;
    private String massage;
    private boolean standPushed;
    private boolean dealPushed;
    private boolean continuePushed;
    private boolean isGame;
    private boolean status;
    private boolean isEnter;
    private String filePath;

    public Engine() {
        decks = 1;
        maxBet = 20;
        minBet = 1;
        decks = 1;
        money = 50;
        connect = (PlayerDAOImpl) ctx.getBean("playerDAO");
        continuePushed = true;
        isGame = false;
        massage = "";
        shoes = new ArrayList<Card>();
        creatDecks(decks);
        mixShoes();
        dealer = (Player) ctx.getBean("dealer");
        filePath = getClass().getResource("/playerSettings.xml").getPath();
    }

    public void setDefaultSettings(){
        player.getSettings().setDecks(decks);
        player.getSettings().setMaxBet(maxBet);
        player.getSettings().setMinBet(minBet);
        player.getSettings().setMoney(money);
        player.setMoney(money);

    }

    public void getSettingsFromXml(String playerName) {
        MyFileReader myFileReader = new MyFileReader();
        MySettings mySettings = myFileReader.getSettingsByNameFromXML(playerName,filePath);
        if (mySettings != null){
            player.setSettings(mySettings);
        } else {
            logger.debug("No player's settings for "+ playerName);
        }

    }

    public void saveNewSettingsToXML(MySettings newSettings){
        MyFileWriter myFileWriter = new MyFileWriter();
        myFileWriter.writeSettingsToFile(newSettings, filePath);
    }

    public void newGame(){
//        try {
//            getSettingsFromXml(player.getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
        creatDecks(decks);
        mixShoes();
        player.clearHand();
        dealer.clearHand();

    }

    public void getPlayerFromDB(String name) {
        try {
            player = connect.getPlayerFromDB(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connect.closeConnection();
    }

    public void oneStep() {
        checkSumPlayerAndDealer();
    }

    public void creatDecks(int num) {
        for (int i = 0; i < num; i++) {
            ArrayList<Card> list = new CardDeck().getCardDeck();
            for (int j = 0; j < list.size(); j++) {
                shoes.add(list.get(j));
            }
        }
    }

    public void takeMoneyFromPlayerToBet(int moneyToBet) {
        if (moneyToBet > player.getMoney()) {
            massage = "You have not enough money!";
        } else if ((moneyToBet + bet) > maxBet) {
            massage = "Max bet: " + maxBet;
        } else {
            setBet(player.takeMoney(moneyToBet));
        }
    }

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

    public ArrayList<Card> getShoes() {
        return shoes;
    }

    public void firstDealToAll() {
        if (bet >= minBet) {
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
            player.addCardToHand(dealOneCard());
            dealer.addCardToHand(dealOneCard());
        } else if (bet < minBet) {
            massage = "Your Bet is to small! Min bet is: " + minBet;
        } else if (bet > maxBet) {
            massage = "Your Bet is to big! Max bet is: " + maxBet;
        }
        oneStep();
    }

    public Card dealOneCard() {
        Card card = shoes.get(0);
        shoes.remove(0);
        return card;
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void dealOneCardToPlayer() {
        if (isGame) {
            player.addCardToHand(dealOneCard());
        }
        oneStep();
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
            status = false;
        }
        if (dealer.getSumNumbers() > 21 && player.getSumNumbers() <= 21) {
            isGame = false;
            massage = "Game over. You won!";
            status = true;
        }
        if (player.getSumNumbers() == 21) {
            isGame = false;
            massage = "Game over. You win! BlackJack";
            status = true;
        }

        if (dealer.getSumNumbers() == 21) {
            isGame = false;
            massage = "Game over. You lose!";
            status = false;
        }

        if (player.getSumNumbers() > dealer.getSumNumbers() && player.getSumNumbers() <= 21) {
            isGame = false;
            massage = "Game over. You won!";
            status = true;
        }

    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
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

    public boolean isStandPushed() {
        return standPushed;
    }

    public void setStandPushed(boolean standPushed) {
        this.standPushed = standPushed;
    }

    public boolean isDealPushed() {
        return dealPushed;
    }

    public void setDealPushed(boolean dealPushed) {
        this.dealPushed = dealPushed;
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
        if (status) {
            playerMoney += bet * 2;
            player.setMoney(playerMoney);
            clearBet();
        }
    }

    public void clearBet() {
        bet = 0;
    }

    public boolean checkPassword(String password) {
        boolean rez = player.getPassword().equals(password) ? true : false;
        return rez;
    }

    public boolean isAvailableName(String name) {
        return connect.isAvailableName(name);
    }

    public void addPlayerToDB(String name, String password, String email) {
        connect.addPlayerToDB(name, password, email);
    }

    public boolean checkNameAndPassword(String name, String password) {
        return connect.checkNameAndPassword(name, password);
    }

    public boolean isEnter() {
        return isEnter;
    }

    public void setEnter(boolean enter) {
        isEnter = enter;
    }

    public void clearPoints(){
        player.clearSumNumbers();
        dealer.clearSumNumbers();
    }



}