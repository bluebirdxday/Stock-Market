package edu.kh.stock_market.dto;

public class Stock { 

	final int STOCK_QUANTITIY = 10;  // 초기 주식 수 
	
	private int stockPrice;	// 주가
	private String stockName;	// 종목명
	private int stockLeft;  // 남은 주식 수 
	private int stockChangeRate; // 변동률
	
	public Stock(int stockPrice, String stockName) {
		
		this.stockPrice = stockPrice;
		this.stockName = stockName;
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
	

	
	
	
}
