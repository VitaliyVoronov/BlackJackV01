package ua.blackjack.model;

/**
 * It's card for blakjack game
 * @author vitaliy
 * @version 1.0
 */

public class Card {
	
	private String dignity;
	private String suit;
	private String face;
	private String shirt;
	private int number;

	
	public Card() {
		
	}

	public Card(String dignity, String suit) {
		face  = "resourses/";
		shirt = "resourses/shirt.png";
		this.dignity = dignity;
		this.suit = suit;
		setNumber();
		setFace();
	}

	public void setNumber() {
		if(dignity.equals("j") || dignity.equals("q") || dignity.equals("k")){
			number = 10;
		} else if(dignity.equals("a")){
			number = 11;
		} else {
			number = Integer.parseInt(dignity);
		}
	}

	public void setFace() {
		face+=dignity+""+suit+".png";
	}

	public void setDignity(String dignity) {
		this.dignity = dignity;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String getDignity() {
		return dignity;
	}
	
	public String getSuit() {
		return suit;
	}

	public int getNumber() {
		return number;
	}

	public String getFace() {
		return face;
	}

	public String getShirt() {
		return shirt;
	}
}
