package ua.blackjack.model;

import org.junit.Test;
import ua.blackjack.engine.Engine;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/30/17
 */
public class CardDeckTest {
    @Test
    public void getCardDeckNumCards() throws Exception {
        CardDeck cardDeck = new CardDeck();
        List<Card> cardList = cardDeck.getCardDeck();
        assertEquals(52,cardList.size());
    }

    @Test
    public void getCardDeckNotNull() throws Exception {
        CardDeck cardDeck = new CardDeck();
        assertNotNull(cardDeck.getCardDeck());
    }

}