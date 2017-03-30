package ua.blackjack.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/30/17
 */
public class CardTest {

    @Test
    public void checkForCreateCard(){
        assertNotNull(new Card("a","c"));
    }

    @Test
    public void checkForCreateFaceCard(){
        Card card = new Card("a","d");
        assertEquals("ad.png",card.getFace().substring(card.getFace().length()-6));
    }

    @Test
    public void checkForCorrectJackNumberCard(){
        Card card = new Card("j","p");
        assertEquals(10,card.getNumber());
    }

    @Test
    public void checkForCorrectAceNumberCard(){
        Card card = new Card("a","p");
        assertEquals(11,card.getNumber());
    }

}