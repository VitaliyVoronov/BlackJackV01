package ua.blackjack.model;

import java.util.ArrayList;

/**
 * It's field for blakjack game
 * @author vitaliy
 * @version 1.0
 */

public class Table {
	
	private ArrayList<Card> shoes = new ArrayList<>();

	public Table(ArrayList<Card> shoes) {
		this.shoes = shoes;
	}

	public ArrayList<Card> getCards() {
		return shoes;
	}

	public void setCards(ArrayList<Card> shoes) {
		this.shoes = shoes;
	}
	
	
	
	

}
