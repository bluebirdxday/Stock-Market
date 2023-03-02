package edu.kh.stock_market.dto;

public class Stock {
	
	
	private int stockPrice;		//주가
	private String stockName;
	private int stockHoldings;  //남은 주식 수 
	private int stockInc;	//상승률
	private int stockDec;	//하락률

	public Stock() {}

	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getStockHoldings() {
		return stockHoldings;
	}

	public void setStockHoldings(int stockHoldings) {
		this.stockHoldings = stockHoldings;
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

	

	public Stock(int stockPrice, int stockHoldings, int stockInc, int stockDec) {
		super();
		this.stockPrice = stockPrice;
		this.stockHoldings = stockHoldings;
		this.stockInc = stockInc;
		this.stockDec = stockDec;
	}


	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public Stock(String stockName, int stockPrice) {
		this.stockName = stockName;
		this.stockPrice = stockPrice;
	}
}
