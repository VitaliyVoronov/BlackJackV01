package ua.blackjack.model;

import java.util.ArrayList;

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
