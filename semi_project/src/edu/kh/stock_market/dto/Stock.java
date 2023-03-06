package edu.kh.stock_market.dto;

import java.util.Objects;

public class Stock {
	
	    private String stockName;	  // 종목명
		private int stockPrice;		// 주가
		private int stockLeft;  // 남은 주식 수 
		private int stockIncDec; // 등락률
		
		
		public Stock() {  }


		public Stock(String stockName, int stockPrice, int stockLeft, int stockIncDec) {
			super();
			this.stockName = stockName;
			this.stockPrice = stockPrice;
			this.stockLeft = stockLeft;
			this.stockIncDec = stockIncDec;
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


		public int getStockIncDec() {
			return stockIncDec;
		}


		public void setStockIncDec(int stockIncDec) {
			this.stockIncDec = stockIncDec;
		}


		@Override
		public int hashCode() {
			return Objects.hash(stockName, stockPrice, stockLeft, stockIncDec);
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
			return stockIncDec == other.stockIncDec && stockLeft == other.stockLeft && stockName == other.stockName
					&& stockPrice == other.stockPrice;
		}


		@Override
		public String toString() {
			return "종목 : " + stockName + " 가격 : " + stockPrice + " 상승/하락률 : " + stockIncDec 
					+ " 남은 주식 수 : " + stockLeft ;
		}
		
		
		
		
		
		
		
		
}


