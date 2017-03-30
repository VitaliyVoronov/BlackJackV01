package ua.blackjack.model;

import java.util.ArrayList;


/**
 * Hand for store player cards
 *
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */
public class Hand {
	
	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList();
	}
	
	public void setCard(Card card) {
		cards.add(card);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	
	
	

}
