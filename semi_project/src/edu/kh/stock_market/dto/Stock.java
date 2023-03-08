package edu.kh.stock_market.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stock {
	
	    private String stockName;	  // 종목명
		private int stockPrice;		// 주가
		private int stockLeft=10;  // 남은 주식 수 
		private int stockIncDec; // 등락률
		private static final int MIN_CHANGE_PERCENT = -10;
	    private static final int MAX_CHANGE_PERCENT = 10;
	    private int prevPrice;
		
		List<Stock> stockList = new ArrayList<>();
		
		public Stock() {
			stockList.add(new Stock("삼성전자", 1000));
	        stockList.add(new Stock("SK하이닉스", 2000));
	        stockList.add(new Stock("NAVER", 3000));
	        stockList.add(new Stock("현대차", 2500));
	        stockList.add(new Stock("삼성바이오로직스", 8000));
	        stockList.add(new Stock("LG화학", 15000));
	        stockList.add(new Stock("삼성SDI", 4000));
	        stockList.add(new Stock("셀트리온", 1500));
	        stockList.add(new Stock("카카오", 18000));
	        stockList.add(new Stock("기아차", 1000));
	        stockList.add(new Stock("POSCO", 2000));
	        stockList.add(new Stock("삼성물산", 3000));
	        stockList.add(new Stock("LG전자", 4000));
	        stockList.add(new Stock("SK이노베이션", 5000));
	        stockList.add(new Stock("삼성생명", 50000));
	        stockList.add(new Stock("현대모비스", 2500));
	        stockList.add(new Stock("LG생활건강", 3000));
	        stockList.add(new Stock("KB금융", 7000));
	        stockList.add(new Stock("한국전력", 6000));
	        stockList.add(new Stock("SK텔레콤", 4000));
		}

		public Stock(String stockName, int stockPrice) {
			super();
			this.stockName = stockName;
			this.stockPrice = stockPrice;
		}

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
			return String.format("종목 : %8s       가격 : %6d원      상승/하락률 : %3d%%     남은 주식 수 : %2d주", 
					stockName, stockPrice, stockIncDec, stockLeft);
//					("종목 : " + stockName + " 가격 : " + stockPrice + " 상승/하락률 : " + stockIncDec 
//					+"%"+ " 남은 주식 수 : " + stockLeft) ;
		}

		public List<Stock> getStockList() {
			return stockList;
		}

		public void setStockList(List<Stock> stockList) {
			this.stockList = stockList;
		}
		
		
		
		
		
		
		
		
}


