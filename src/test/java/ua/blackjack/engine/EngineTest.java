package ua.blackjack.engine;

import org.junit.Before;
import org.junit.Test;
import ua.blackjack.model.Player;

import static org.junit.Assert.*;

/**
 * Tests for Engine class
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/29/17
 */
public class EngineTest {

    Engine engine;

    @Before
    public void setUp() throws Exception {
        engine = new Engine();
    }

    @Test
    public void signInSuccess() throws Exception {
        assertTrue(engine.signIn("test","11111"));
    }

    @Test
    public void signInIncorrectPassword() throws Exception {
        assertFalse(engine.signIn("test","22222"));
    }

    @Test
    public void signInIncorrectLogin() throws Exception {
        assertFalse(engine.signIn("testFail","11111"));
    }

    @Test
    public void signInIncorrectLoginAndPassword() throws Exception {
        assertFalse(engine.signIn("testFail","22222"));
    }

    @Test
    public void signInWithoutLoginAndPassword() throws Exception {
        assertFalse(engine.signIn("",""));
    }

    @Test
    public void signInWithoutLogin() throws Exception {
        assertFalse(engine.signIn("","11111"));
    }

    @Test
    public void signInWithoutPassword() throws Exception {
        assertFalse(engine.signIn("test",""));
    }

    @Test
    public void isAvailableLoginFalse() throws Exception {
        assertFalse(engine.isAvailableLogin("test"));
    }

    @Test
    public void isAvailableLoginTrue() throws Exception {
        assertTrue(engine.isAvailableLogin("notestlogin"));
    }

    @Test
    public void isAvailableLoginWithoutLogin() throws Exception {
        assertFalse(engine.isAvailableLogin(""));
    }

    @Test
    public void setDefaultSettingsWithoutPlayer() throws Exception {
        assertFalse(engine.setDefaultSettings());
    }

    @Test
    public void setDefaultSettingsWithPlayer() throws Exception {
        engine.setPlayer(new Player("test"));
        assertTrue(engine.setDefaultSettings());
    }

    @Test
    public void setDefaultSettingsSuccess() throws Exception {
        engine.setPlayer(new Player("test"));
        engine.setDefaultSettings();
        Player player = engine.getPlayer();
        assertEquals(2,player.getSettings().getDecks());
        assertEquals(5,player.getSettings().getMinBet());
        assertEquals(10,player.getSettings().getMaxBet());
        assertEquals(100,player.getSettings().getMoney());
    }

    @Test
    public void getSettingsFromXmlWithoutPlayer() throws Exception {
        assertFalse(engine.getSettingsFromXmlAndSetItToPlayer(""));
    }

    @Test
    public void getSettingsFromXmlSuccess() throws Exception {
        engine.setPlayer(new Player("test"));
        assertTrue(engine.getSettingsFromXmlAndSetItToPlayer(engine.getPlayer().getName()));
        assertNotNull(engine.getPlayer().getSettings());
    }

}