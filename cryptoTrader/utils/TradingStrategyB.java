package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TradingStrategyB.java
 * This class is used to create transactions of TradingStartegyB
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */

public class TradingStrategyB extends TradingStrategy{

	private static String decision = "Buy";
	private static String quantity = "200";
	private static String [] reqCoins = {"ETH"};
	private static String strategy = "Strategy-B";

	/*
	 * Constructor for TradingStrategyB objects
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @param date - date of transaction
	 */

	public TradingStrategyB(String trader, String[] coin, String date) {
		super(trader, strategy, reqCoins, decision, quantity,"", date);

	}

	/*
	 * Check if trades of TradingStrategyB object should be executed
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @return returns TradingStrategy object if trade fits strategy requirements
	 */

	public TradingStrategy executeB(String trader, String [] coin) {
		DataFetcher data = new DataFetcher();
		Date newDate = new Date();  
		int counter = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(newDate);  
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMMM-yyyy");
		String date1 = formatter1.format(newDate);
		double price = data.getPriceForCoin("ethereum", date);
		price = price * 100;
		price = (double)((int)price);
		price = price/ 100;

		// Creates TradingStrategy object for trade
		TradingStrategy trade = new TradingStrategy(trader, strategy, reqCoins, decision, quantity, Double.toString(price), date1);

		// Counts the number of coins attached to TradingStrategy object
		for (int i = 0; i < coin.length; i++) {
			for (int j = 0; j < reqCoins.length; j++) {

				if (coin[i].equals(reqCoins[j]) == true) {
					counter++;
				}		
			}
		}

		// If there are enough coins and ETH has a price over 4000,
		// then return TradingStrategy object, otherwise return null
		if (counter == reqCoins.length && price > 4000) {
			return trade;
		}
		else {
			return null;
		}


	}





}
