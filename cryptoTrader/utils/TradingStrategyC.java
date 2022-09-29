package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TradingStrategyC.java
 * This class is used to create transactions of TradingStartegyC
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */

public class TradingStrategyC extends TradingStrategy{

	private static String decision = "Sell";
	private static String quantity = "150";
	private static String [] reqCoins = {"ETH", "BTC"};
	private static String strategy = "Strategy-C";

	/*
	 * Constructor for TradingStrategyC objects
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @param date - date of transaction
	 */

	public TradingStrategyC(String trader, String[] coin, String date) {
		super(trader, strategy, reqCoins, decision, quantity,"", date);

	}

	/*
	 * Check if trades of TradingStrategyC object should be executed
	 * @param trader - the name of the trader
	 * @param coin - list of the coins
	 * @return returns TradingStrategy object if trade fits strategy requirements
	 */

	public TradingStrategy executeC(String trader, String [] coin) {
		DataFetcher data = new DataFetcher();
		Date newDate = new Date();  
		int counter = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(newDate);  
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMMM-yyyy");
		String date1 = formatter1.format(newDate);
		double priceETH = data.getPriceForCoin("ethereum", date);
		double priceBTC = data.getPriceForCoin("bitcoin", date);
		priceETH = priceETH * 100;
		priceETH = (double)((int)priceETH);
		priceETH = priceETH / 100;

		// Creates TradingStrategy object for trade
		TradingStrategy trade = new TradingStrategy(trader, strategy, reqCoins, decision, quantity, Double.toString(priceETH), date1);

		// Counts the number of coins attached to TradingStrategy object
		for (int i = 0; i < coin.length; i++) {
			for (int j = 0; j < reqCoins.length; j++) {

				if (coin[i].equals(reqCoins[j]) == true) {
					counter++;
				}		
			}
		}

		// If there are enough coins, ETH has a price less than 4000 and BTC has price less than 50000,
		// then return TradingStrategy object, otherwise return null
		if (counter == reqCoins.length && priceETH < 4000 && priceBTC < 50000) {
			return trade;
		}
		else {
			return null;
		}

	}





}
