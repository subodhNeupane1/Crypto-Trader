package cryptoTrader.utils;

/**
 * TradingStrategy.java
 * The class is used to set various information needed for the different strategies
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */
public class TradingStrategy {


	String trader, strategy, decision, date;
	String[] coin;
	String quantity;
	String price;
	/*
	 * Constructor class for the trading strategy which sets various informations for the strategies
	 * @param trader- name of the trader
	 * @param strategy - name of the strategy
	 * @param coin[]- list of all the coins
	 * @param decision - the action of the trader, buy or sell
	 * @oaram quantity - the quantity of the coins
	 * @param price - the price of the coins
	 * @param date - the current date
	 */
	public TradingStrategy(String trader, String strategy, String [] coin, String decision, String quantity, String price, String date) {
		this.trader = trader;
		this.strategy = strategy;
		this.coin = coin;
		this.decision = decision;
		this.date = date;
		this.quantity = quantity;
		this.price = price;	
	}

	/*
	 * Getter method which returns the name of the trader
	 * @return name of the trader
	 */
	public String getTrader() {
		return trader;
	}
	
	/*
	 * Getter method which returns the name of the strategy
	 * @return name of the trader
	 */
	public String getStrategy() {
		return strategy;
	}
	
	/*
	 * Getter method which returns the list of the coins
	 * @return list of the coins
	 */
	public String[] getCoin() {
		return coin;
	}
	/*
	 * Getter method which returns a string with all of the coins
	 * @return string with all of the coins
	 */
	public String getCoins() {
		String coins = "";
		for (int i = 0; i < coin.length -1; i++) {
			// Each coin name is separated with a comma
			coins += coin[i] + ", ";
		}
		coins += coin[coin.length - 1];
		return coins;
	}
	
	/*
	 * Getter method which returns the decision of the trader
	 * @return decision of the trader
	 */
	public String getDecision() {
		return decision;
	}
	/*
	 * Getter method which returns the date
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/*
	 * Getter method which returns the quantity of the coins
	 * @return the quantity of coins
	 */
	public String getQuantity() {
		return quantity;
	}
	
	/*
	 * Getter method which returns the price of the coins
	 * @return the price of the coins
	 */
	public String getPrice() {
		return price;
	}








}
