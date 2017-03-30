package ua.blackjack.model;

import java.util.ArrayList;

/**
 * It's card deck for blakjack game
 * @author vitaliy
 * @version 1.0
 */

public class CardDeck {
	
	private ArrayList<Card> cardDeck = new ArrayList<>();
	private String[] dignity = {"a","k","q","j","10","9","8","7","6","5","4","3","2"};
	private String[] suit = {"c","p","h","d"};

	public CardDeck() {
		createDeck();
	}
	
	private void createDeck(){
		for(int i = 0; i < dignity.length; i++){
			for(int j = 0; j < suit.length; j++){
				cardDeck.add(new Card(dignity[i], suit[j]));
			}
		}
	}

	public ArrayList<Card> getCardDeck() {
		return cardDeck;
	}
	
	
	
	

}
