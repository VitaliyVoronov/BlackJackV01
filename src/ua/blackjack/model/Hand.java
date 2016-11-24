package ua.blackjack.model;

import java.util.ArrayList;

public class Hand {
	
	ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void setCard(Card card) {
		cards.add(card);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	
	
	

}
