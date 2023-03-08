package edu.kh.stock_market.dto;

import java.util.List;

public class User {
	
	

	public final int START_CASH = 100_000; // 시작시 현금량
	final String SCREEN = "KH Stock Market";
	public static final int STOCK_QUANTITIY = 10; // 초기 주식 수

	private int day = 1; // 날짜
	private int turn; // 턴
	private String userName; // 유저이름
	private int userNum; // 유저순서
	private int cashHoldings; // 보유 현금
	private List<Stock> stocks;
	private int property; // 총 자산
	private int bidPrice; // 입찰가

	public User() {
		cashHoldings = START_CASH;

	}

	public User(String userName) {
		this();
		this.userName = userName;
	}

	public User(int cashHoldings) {
		this();
		this.cashHoldings = START_CASH;
	}

	@Override
	public String toString() {
		return userName;
	}

	public User(int day, int turn, String userName, int userNum, int cashHoldings, List<Stock> stocks, int property,
			int bidPrice) {
		super();
		this.day = day;
		this.turn = turn;
		this.userName = userName;
		this.userNum = userNum;
		this.cashHoldings = cashHoldings;
		this.stocks = stocks;
		this.property = property;
		this.bidPrice = bidPrice;
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

}