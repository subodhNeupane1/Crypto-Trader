package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;


/**
 * DataVisualizationCreator.java
 * This class is used to create the visuals of the data that is inputted by the user along with the price and the date
 * @author Subodh Neupane, Shehroz Bajwa, Thanish Premarajan and Isabella Lu
 */

public class DataVisualizationCreator {

	private static ArrayList<TradingStrategy> strategyList= new ArrayList<>();
	public void createCharts() {
		//		createTextualOutput();
		createTableOutput();
		//		createTimeSeries();
		//		createScatter();
		createBar();
	}

	private void createTextualOutput() {
		//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"Broker Name", "Ticker List", "Strategy Name"}, 1);
		//		JTable table = new JTable(dtm);
		//		//table.setPreferredSize(new Dimension(600, 300));
		//		dtm.e
		//		JScrollPane scrollPane = new JScrollPane(table);
		//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
		//                "Broker Actions",
		//                TitledBorder.CENTER,
		//                TitledBorder.TOP));
		//		
		//	
		//		
		//		scrollPane.setPreferredSize(new Dimension(800, 300));
		//		table.setFillsViewportHeight(true);;

		//		MainUI.getInstance().updateStats(scrollPane);
	}

	private void createTableOutput() {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};

		// Dummy data for demo purposes. These should come from actual fetcher

		String [] coins1 = {"BTC"};
		String [] coins2 = {"ETH"};
		BufferedReader bf;
		String strategyName = "", traderName = "";
		String [] coinNames = null;
		try {
			//The file for the trade queue is read
			bf = new BufferedReader(new FileReader("src/main/java/cryptoTrader/tradeQueue.txt"));
			traderName = bf.readLine();
			//The brackets for the list of coins are removed along with the spaces
			coinNames = bf.readLine().replace("[","").replace("]", "").replace(" ","").split(",");

			strategyName = bf.readLine();

		} catch (Exception e) {
		}
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("src/main/java/cryptoTrader/tradeQueue.txt"));
			write.write("");
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Correct strategy is selected
		if (strategyName.equals("Strategy-A")) {
			TradingStrategy stratA = new TradingStrategyA(traderName, coinNames, strategyName);
			stratA = ((TradingStrategyA) stratA).executeA(traderName, coinNames);
			strategyList.add(stratA);
		}
		else if (strategyName.equals("Strategy-B")) {
			TradingStrategy stratB = new TradingStrategyB(traderName, coinNames, strategyName);
			stratB = ((TradingStrategyB) stratB).executeB(traderName, coinNames);
			strategyList.add(stratB);
		}
		else if (strategyName.equals("Strategy-C")) {
			TradingStrategy stratC= new TradingStrategyC(traderName, coinNames, strategyName);
			stratC = ((TradingStrategyC) stratC).executeC(traderName, coinNames);
			strategyList.add(stratC);
		}
		else if (strategyName.equals("Strategy-D")) {
			TradingStrategy stratD = new TradingStrategyD(traderName, coinNames, strategyName);
			stratD = ((TradingStrategyD) stratD).executeD(traderName, coinNames);
			strategyList.add(stratD);
		}

		
		//An 2d array of objects are created for the list of trades
		Object [][] data = new Object[strategyList.size()][7];
		for (int i = 0; i < strategyList.size(); i++) {
			data[i][0] = strategyList.get(i).getTrader();
			data[i][1] = strategyList.get(i).getStrategy();
			data[i][2] = strategyList.get(i).getCoins();
			data[i][3] = strategyList.get(i).getDecision();
			data[i][4] = strategyList.get(i).getQuantity();
			data[i][5] = strategyList.get(i).getPrice();
			data[i][6] = strategyList.get(i).getDate();
		}

		//A table is created 
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
				"Trader Actions",
				TitledBorder.CENTER,
				TitledBorder.TOP));



		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;

		MainUI.getInstance().updateStats(scrollPane);
	}

	private void createTimeSeries() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);

		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		//plot.mapDatasetToRangeAxis(2, 2);// 3rd dataset to 3rd y-axis

		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);

		MainUI.getInstance().updateStats(chartPanel);
	}

	private void createScatter() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);

		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart scatterChart = new JFreeChart("Daily Price Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

	private void createBar() {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//		Those are hard-coded values!!!! 
		//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		

		Object [][] data = new Object[strategyList.size()][3];
		for (int i = 0; i < strategyList.size(); i++) {

			data[i][0] = strategyList.get(i).getTrader();
			data[i][1] = strategyList.get(i).getStrategy();
			data[i][2] = 0;
		}
		double counter = 0;
		//The bar graph is created using all of the data from the tables
		for (int i = 0; i < data.length; i++) {
			counter = 0;
			for (int j = 0; j < data.length; j++) {
				
				if (data[i][0] != null && data[i][0].equals(data[j][0]) == true && data[i][1].equals(data[j][1]) == true) {
					if (i != j) {
						data[j][0] = null;
					}
					counter++;
					data[i][2] = counter;
				}
			}
		}
		for (int i = 0; i < data.length; i++) {
			
			if (data[i][0] != null) {
			 dataset.setValue(((double)data[i][2]), (String)data[i][0], (String)data[i][1]);
			}
		}
		

	


		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}