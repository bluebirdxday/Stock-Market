package edu.kh.stock_market.dto;

import java.util.HashSet;
import java.util.Set;

public class Stock { 

	final int STOCK_QUANTITIY = 10;  // 초기 주식 수 
	
	private int stockPrice;	// 주가
	private String stockName;	// 종목명
	private int stockLeft;  // 남은 주식 수 
	private int stockChangeRate; // 변동률
	
	final String[][] stockData = { {"1000", "한미약품"}, {"1500", "삼성바이오로직스"}, {"1000", "대한항공"}, {"500", "모두투어"}};
	private Set<Stock> stockList;
	
	public Stock() {
		
		stockList = new HashSet<>();
		
		for(int i=0; i<stockData.length; i++) {
			 stockList.add(new Stock(Integer.parseInt(stockData[i][0]), stockData[i][1]));
		}
	
	}
	
	public Stock(int stockPrice, String stockName) {
	
		this.stockPrice = stockPrice;
		this.stockName = stockName;
		this.stockChangeRate = 0;
		this.stockLeft = STOCK_QUANTITIY;
	}

	public int getStockPrice() {
		return stockPrice;
	}
	
	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public int getStockLeft() {
		return stockLeft;
	}
	
	public void setStockLeft(int stockLeft) {
		this.stockLeft = stockLeft;
	}

	public int getStockChangeRate() {
		return stockChangeRate;
	}

	public void setStockChangeRate(int stockChangeRate) {
		this.stockChangeRate = stockChangeRate;
	}


	public Set<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(Set<Stock> stockList) {
		this.stockList = stockList;
	}

	@Override
	public String toString() {
		return stockName + " : " + stockPrice;
	}
	

	
	
}
