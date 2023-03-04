package edu.kh.stock_market.dto;

public class Stock {
	
	
	private int stockPrice;		//현재주가
	private int stockYesPrice;   //전날주가
	public int getStockYesPrice() {
		return stockYesPrice;
	}

	public void setStockYesPrice(int stockYesPrice) {
		this.stockYesPrice = stockYesPrice;
	}

	private String stockName;
	
	private int stockRemain;  //남은 주식 수 
	private double stockIncDec;	//등락률

	public Stock() {}

	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}

	public int getStockRemain() {
		return stockRemain;
	}

	public void setStockRemain(int stockRemain) {
		this.stockRemain = stockRemain;
	}

	public double getStockIncDec() {
		return stockIncDec;
	}

	public void setStockIncDec(double stockIncDec) {
		this.stockIncDec = stockIncDec;
	}



	

	public Stock(int stockPrice,int stockIncDec) {
		super();
		this.stockPrice = stockPrice;
		this.stockIncDec = stockIncDec;
	}
	
	public Stock(int stockRemain) {
		this.stockRemain = 10;
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
