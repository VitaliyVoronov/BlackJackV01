package ua.blackjack.model;

public class Player {

	private int playerID;
	private String name = null;
	private int money = 0;
	private int sumNumbers = 0;
	private Hand hand = new Hand();
	private String email;
	private String password;

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

	public Hand getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public int getSumNumbers() {
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

	public void setSettingsParameters(int decks,int minBet,int maxBet,int money) {
		setMoney(money);
		settings.setDecks(decks);
		settings.setMinBet(minBet);
		settings.setMaxBet(maxBet);
		settings.setMoney(money);
	}

	public void clearSumNumbers() {
		sumNumbers = 0;
	}
}


