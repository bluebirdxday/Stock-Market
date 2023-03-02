package edu.kh.stock_market.dto;

public class Stock { 

	final int STOCK_QUANTITIY = 10;  // 초기 주식 수 
	
	private int stockPrice;	// 주가
	private int stockName;	// 종목명
	private int stockLeft;  // 남은 주식 수 
	private int stockInc;	// 상승률
	private int stockDec;	// 하락률
	
	
	
	public Stock(int stockPrice, int stockName) {
		
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
	
	public int getStockName() {
		return stockName;
	}
	
	public void setStockName(int stockName) {
		this.stockName = stockName;
	}
	
	public int getStockLeft() {
		return stockLeft;
	}
	
	public void setStockLeft(int stockLeft) {
		this.stockLeft = stockLeft;
	}
	
	public int getStockInc() {
		return stockInc;
	}
	
	public void setStockInc(int stockInc) {
		this.stockInc = stockInc;
	}
	
	public int getStockDec() {
		return stockDec;
	}
	
	public void setStockDec(int stockDec) {
		this.stockDec = stockDec;
	}
	
	
	
}
