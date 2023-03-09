package edu.kh.stock_market.dto;

public class UserStock {
	
	private String stockName; // 종목명
	private int stockCount;   // 보유 주식 수
	private int stockMarketValue;  // 보유 주식 평가 금액
	private int averagePrice; // 평균단가


	public UserStock(String stockName, int stockCount,int averagePrice) {
		this.stockName = stockName;
		this.stockCount = stockCount;
		this.averagePrice = averagePrice;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}


	public int getStockCount() {
		return stockCount;
	}


	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}


	public int getStockMarketValue() {
		return stockMarketValue;
	}


	public void setStockMarketValue(int stockMarketValue) {
		this.stockMarketValue = stockMarketValue;
	}


	public int getAveragePrice() {
		return averagePrice;
	}


	public void setAveragePrice(int averagePrice) {
		this.averagePrice = averagePrice;
	}


	@Override
	public boolean equals(Object obj) {
		UserStock userStock = (UserStock) obj;
		
		if(userStock.stockName == this.stockName)
			return true;
		
		return false;
	}


	@Override
	public int hashCode() {
		return stockName.hashCode();
	}

	
	
}
