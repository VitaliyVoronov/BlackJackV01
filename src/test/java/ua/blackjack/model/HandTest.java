package ua.blackjack.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/30/17
 */
public class HandTest {
    @Test
    public void checkForCorrectAddCardsToHand() throws Exception {
        Hand hand = new Hand();
        hand.setCard(new Card("a","c"));
        hand.setCard(new Card("a","p"));
        hand.setCard(new Card("a","h"));
        assertEquals(3,hand.getCards().size());
    }

}