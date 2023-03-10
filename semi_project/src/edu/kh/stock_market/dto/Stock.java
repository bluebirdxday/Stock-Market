package edu.kh.stock_market.dto;

public class Stock {
	
	private String stockName;
	private int stockPrice;		// 현재주가
	private int stockYesPrice;   // 전날주가
	private int updatePrice;
	private int prevPrice;
	private int stockRemain;  // 남은 주식 수 
	private double stockIncDec;	// 등락률


	public Stock() {
	}

	public Stock(String stockName, int stockPrice) {
		this.stockName = stockName;
		this.stockPrice = stockPrice;
		this.prevPrice = stockPrice;
	}
	
	public Stock(int stockPrice, int stockIncDec) {
		this.stockPrice = stockPrice;
		this.stockIncDec = stockIncDec;
	}
	
	public Stock(int stockRemain) {
		this.stockRemain = 10;
	}
	
	
	
	public int getPrevPrice() {
		return prevPrice;
	}

	public void setPrevPrice(int prevPrice) {
		this.prevPrice = prevPrice;
	}

	public int getStockYesPrice() {
		return stockYesPrice;
	}

	public void setStockYesPrice(int stockYesPrice) {
		this.stockYesPrice = stockYesPrice;
	}


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


	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public void updatePrice() {
		
	}

	public int getUpdatePrice() {
		return updatePrice;
	}

	public void setUpdatePrice(int updatePrice) {
		this.updatePrice = updatePrice;
	}
	public double getChangeRate() {
        return (stockPrice - prevPrice) / (double) prevPrice * 100.0;
    }
	
	public String getStockInfo() {
		return String.format("%s : %,d원 (%.2f%%)", stockName, stockPrice, getChangeRate());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockName == null) ? 0 : stockName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (stockName == null) {
			if (other.stockName != null)
				return false;
		} else if (!stockName.equals(other.stockName))
			return false;
		return true;
	}
	
	
	
	
	
}