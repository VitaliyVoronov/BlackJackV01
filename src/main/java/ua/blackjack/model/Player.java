package ua.blackjack.model;

public class Player {

	private int playerID;
	private String name;
	private int money;
	private int sumNumbers;
	private String email;
	private String password;

	private Hand hand = new Hand();
	private MySettings settings = new MySettings();

	public Player() {
	}

	public Player(String name) {
		this.name = name;
		setSettingsName();
	}

	public void addCardToHand(Card card){
		hand.setCard(card);
		setSumNumbers(card.getNumber());
	}

	public void setName(String name) {
		this.name = name;
		setSettingsName();
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setSumNumbers(int sumNumbers) {
		this.sumNumbers += sumNumbers;
	}

	public void countCardsNumbers(){
		int tempSum = 0;
		int sumA = 0;
		for(Card c : hand.getCards()){
			tempSum += c.getNumber();
			if (c.getDignity() == "a"){
				sumA++;
			}
		}
		if (tempSum > 21 && sumA > 0){
			tempSum -= sumA * 10;
		}
		sumNumbers = tempSum;

	}

	public Hand getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public int getSumNumbers() {
		countCardsNumbers();
		return sumNumbers;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int takeMoney(int moneyToBet){
		money -= moneyToBet;
		return moneyToBet;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void clearHand(){
		hand.getCards().clear();
	}

	public MySettings getSettings() {
		return settings;
	}

	public void setSettings(MySettings settings) {
		this.settings = settings;
		setMoney(settings.getMoney());
	}

	public void setSettingsName(){
		settings.setName(name);
	}

	public void clearSumNumbers() {
		sumNumbers = 0;
	}
}


