package edu.kh.stock_market.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	final int START_CASH = 100_000;	//시작시 현금
		
	private String userName;	  // 유저 이름
	private int cashHoldings;	  // 보유 현금
	private List<Stock> stocks;   // 보유 종목
	private int property;         // 총 자산
	private int bidPrice;		  // 입찰가
	
	
	public User(String userName) {
		this.userName = userName;
		cashHoldings = START_CASH;
		stocks = new ArrayList<Stock>();
		property = START_CASH;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getCashHoldings() {
		return cashHoldings;
	}


	public void setCashHoldings(int cashHoldings) {
		this.cashHoldings = cashHoldings;
	}


	public List<Stock> getStocks() {
		return stocks;
	}


	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}


	public int getProperty() {
		return property;
	}


	public void setProperty(int property) {
		this.property = property;
	}


	public int getBidPrice() {
		return bidPrice;
	}


	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	
	
	
}
