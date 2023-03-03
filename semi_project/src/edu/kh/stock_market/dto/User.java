package edu.kh.stock_market.dto;

import java.util.List;
import java.util.Objects;

public class User {
	
	final int START_CASH = 100_000;				//시작시 현금량
	final String SCREEN = "KH Stock Market";
	final int STOCK_QUANTITIY = 10; 			//초기 주식 수 
	
	private int day;			    //날짜
	private int turn;			    //턴
	private String userName;	//유저이름
	private int userNum;		  //유저순서
	private int cashHoldings;	//보유 현금
    private List<Stock> stocks; 
	private int property;		  // 총 자산
	private int bidPrice;		  // 입찰가
	
	public User() {
		
	}
	

	public User(String userName) {
		this.userName = userName;
	}
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
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
	public int getSTART_CASH() {
		return START_CASH;
	}
	public String getSCREEN() {
		return SCREEN;
	}
	public int getSTOCK_QUANTITIY() {
		return STOCK_QUANTITIY;
	}


	@Override
	public int hashCode() {
		return Objects.hash(SCREEN, START_CASH, STOCK_QUANTITIY, bidPrice, cashHoldings, day, property, stocks, turn,
				userName, userNum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(SCREEN, other.SCREEN) && START_CASH == other.START_CASH
				&& STOCK_QUANTITIY == other.STOCK_QUANTITIY && bidPrice == other.bidPrice
				&& cashHoldings == other.cashHoldings && day == other.day && property == other.property
				&& Objects.equals(stocks, other.stocks) && turn == other.turn
				&& Objects.equals(userName, other.userName) && userNum == other.userNum;
	}
	
}
