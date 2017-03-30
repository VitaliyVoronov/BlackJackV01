package ua.blackjack.model;

/**
 * Card model for create cards
 *
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class Card {
	
	private String dignity;
	private String suit;
	private String face;
	private String shirt;
	private int number;

	public Card() {
	}

	/**
	 * Constructor for created card
	 * @param dignity like "a" it's Ace
	 * @param suit like "c" it's Cross
	 */
	public Card(String dignity, String suit) {
	    //TODO It's not good idea. All path I need store in engine or perhaps in special file
		face  = "resources/images/";
		shirt = "resources/images/shirt.png";
		this.dignity = dignity;
		this.suit = suit;
		setNumber();
		setFace();
	}

    /**
     * Service method
     * Set number for new card if it's Queen thea number equally 10
     */
	public void setNumber() {
		if(dignity.equals("j") || dignity.equals("q") || dignity.equals("k")){
			number = 10;
		} else if(dignity.equals("a")){
			number = 11;
		} else {
			number = Integer.parseInt(dignity);
		}
	}

    /**
     * Service method
     * Create full name by card with path to the folder with card
     */
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
