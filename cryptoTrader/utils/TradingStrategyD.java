package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TradingStrategyD.java
 * This class is used to create transactions of TradingStartegyD
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */

public class TradingStrategyD extends TradingStrategy{

	private static String decision = "Sell";
	private static String quantity = "125";
	private static String [] reqCoins = {"ADA", "BTC"};
	private static String strategy = "Strategy-D";

	/*
	 * Constructor for TradingStrategyD objects
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @param date - date of transaction
	 */

	public TradingStrategyD(String trader, String[] coin, String date) {
		super(trader, strategy, reqCoins, decision, quantity,"", date);

	}

	/*
	 * Check if trades of TradingStrategyD object should be executed
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @return returns TradingStrategy object if trade fits strategy requirements
	 */

	public TradingStrategy executeD(String trader, String [] coin) {
		DataFetcher data = new DataFetcher();
		Date newDate = new Date();  
		int counter = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");   // getting correct format for date required for api
		String date = formatter.format(newDate);  
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMMM-yyyy");
		String date1 = formatter1.format(newDate);
		double priceADA = data.getPriceForCoin("cardano", date);
		double priceBTC = data.getPriceForCoin("bitcoin", date);
		priceADA = priceADA * 100;        // making price 2 decimal points
		priceADA = (double)((int)priceADA);
		priceADA = priceADA / 100;

		// Creates TradingStrategy object for trade
		TradingStrategy trade = new TradingStrategy(trader, "Strategy-D", reqCoins, decision, quantity, Double.toString(priceADA), date1);

		// Counts the number of coins attached to TradingStrategy object trader
		for (int i = 0; i < coin.length; i++) {
			for (int j = 0; j < reqCoins.length; j++) {

				if (coin[i].equals(reqCoins[j]) == true) {
					counter++;
				}		
			}
		}

		// If there are enough coins, ADA has a price less than 2 and BTC has price less than 55000, 
		// then return TradingStrategy object, otherwise return null
		if (counter == reqCoins.length && priceADA < 2 && priceBTC < 55000) {
			return trade;
		}
		else {
			return null;
		}

	}





}
