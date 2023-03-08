package edu.kh.stock_market.dto;

import java.util.HashSet;
import java.util.Set;

public class Stock { 

	final int STOCK_QUANTITIY = 10;  // 초기 주식 수 
	
	private int stockPrice;	// 주가
	private String stockName;	// 종목명
	private int stockLeft;  // 남은 주식 수 
	private int stockChangeRate; // 변동률
	
	final String[][] stockData = { {"1000", "한미약품"}, {"1500", "삼성바이오로직스"}, {"1000", "대한항공"},
			{"500", "모두투어"}, {"2500", "테슬라"}, {"2500", "마이크로소프트"}, {"1500", "현대모비스"},
			{"1000", "기아"}, {"500", "에스엠"}, {"1500", "하이브"}, {"500", "DB하이텍"}, {"2000", "SK하이닉스"},
			{"2000", "애플"}, {"2500", "삼성전자"}, {"1000", "KB금융"}, {"500", "미래에셋대우증권"}, {"500", "현대건설"},
			{"1500", "엘에이치"}, {"1000", "한화손해보험"}, {"2000", "DB손해보험"}, {"2500", "코스모화학"}, {"1500", "SK종합화학"},
			{"1000", "씨제이"}, {"500", "오뚜기"}, {"500", "아모레퍼시픽"}, {"1000", "생활건강"}};
	
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
		stockPrice += stockPrice * stockChangeRate/100;
		
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
