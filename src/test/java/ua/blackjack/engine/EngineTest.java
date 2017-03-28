package ua.blackjack.engine;

import org.junit.Before;
import org.junit.Test;
import ua.blackjack.model.MySettings;
import ua.blackjack.model.Player;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/27/17
 */
public class EngineTest {

//    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    Engine engine = null;

    @Before
    public void setup(){
        engine = new Engine(); //(Engine) ctx.getBean("engine");
    }

//    @Test
//    public void setDefaultSettings() throws Exception {
//
//    }

    @Test
    public void getSettingsFromXml() throws Exception {
        engine.getSettingsFromXml("test");
        MySettings settings = engine.getPlayer().getSettings();
        assertEquals(1,settings.getDecks());
        assertEquals(1,settings.getMinBet());
        assertEquals(2,settings.getMaxBet());
        assertEquals(10,settings.getMoney());
    }

//    @Test
//    public void saveNewSettingsToXML() throws Exception {
//
//    }
//
//    @Test
//    public void newGame() throws Exception {
//
//    }
//
//    @Test
//    public void getPlayerFromDB() throws Exception {
//        engine.getPlayerFromDB("test");
//        Player testPlayer = engine.getPlayer();
//        assertNotNull(testPlayer);
//        assertEquals("test",testPlayer.getName());
//    }
//
//    @Test
//    public void oneStep() throws Exception {
//
//    }
//
    @Test
    public void createDecksAndAddToShoes() throws Exception {
        engine.createDecksAndAddToShoes(0);
        assertEquals(52, engine.getShoes().size());
    }
//
//    @Test
//    public void takeMoneyFromPlayerToBet() throws Exception {
//
//    }
//
//    @Test
//    public void getShoes() throws Exception {
//
//    }
//
//    @Test
//    public void firstDealToAll() throws Exception {
//
//    }
//
//    @Test
//    public void dealOneCard() throws Exception {
//
//    }
//
//    @Test
//    public void getDealer() throws Exception {
//
//    }
//
//    @Test
//    public void getPlayer() throws Exception {
//
//    }
//
//    @Test
//    public void dealOneCardToPlayer() throws Exception {
//
//    }
//
//    @Test
//    public void dealCardsToDealer() throws Exception {
//
//    }
//
//    @Test
//    public void checkSumPlayerAndDealer() throws Exception {
//
//    }
//
//    @Test
//    public void getMassage() throws Exception {
//
//    }
//
//    @Test
//    public void setMassage() throws Exception {
//
//    }
//
//    @Test
//    public void setBet() throws Exception {
//
//    }
//
//    @Test
//    public void getBet() throws Exception {
//
//    }
//
//    @Test
//    public void setGameTrue() throws Exception {
//
//    }
//
//    @Test
//    public void setGameFalse() throws Exception {
//
//    }
//
//    @Test
//    public void isGame() throws Exception {
//
//    }
//
//    @Test
//    public void isStandPushed() throws Exception {
//
//    }
//
//    @Test
//    public void setStandPushed() throws Exception {
//
//    }
//
//    @Test
//    public void isDealPushed() throws Exception {
//
//    }
//
//    @Test
//    public void setDealPushed() throws Exception {
//
//    }
//
//    @Test
//    public void isContinuePushed() throws Exception {
//
//    }
//
//    @Test
//    public void setContinuePushed() throws Exception {
//
//    }
//
//    @Test
//    public void clearTable() throws Exception {
//
//    }
//
//    @Test
//    public void countWin() throws Exception {
//
//    }
//
//    @Test
//    public void clearBet() throws Exception {
//
//    }
//
//    @Test
//    public void checkPassword() throws Exception {
//
//    }
//
//    @Test
//    public void isAvailableLogin() throws Exception {
//
//    }
//
//    @Test
//    public void addPlayerToDB() throws Exception {
//
//    }
//
//    @Test
//    public void checkNameAndPassword() throws Exception {
//
//    }
//
//    @Test
//    public void isEnter() throws Exception {
//
//    }
//
//    @Test
//    public void setEnter() throws Exception {
//
//    }
//
//    @Test
//    public void clearPoints() throws Exception {
//
//    }

}