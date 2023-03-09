package edu.kh.stock_market.dto;

import java.util.ArrayList;
import java.util.List;


public class User {
	private int cash = 100000; // 시작시 현금
	private String userName; // 유저 이름
	private int cashHoldings; // 보유 현금
	private List<UserStock> userStockList; // 보유 종목
	private int property; // 총 자산

	public User() {
	}

	public User(String userName) {
		this.userName = userName;
		cashHoldings = cash;
		userStockList = new ArrayList<>();
		property = cash;
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

	public int getProperty() {
		return property;
	}
	
	

	public List<UserStock> getUserStockList() {
		return userStockList;
	}

	public void setUserStockList(List<UserStock> userStockList) {
		this.userStockList = userStockList;
	}

	

	// 총 자산 업데이트 (매수, 매도 시)
	public void updateProperty(int totalPrice) {
		cashHoldings -= totalPrice; // 보유현금 = 보유 현금 - 매수 종목 가격 * 매수 주식 수
		property = cashHoldings + totalPrice; // 총자산 = 보유현금 + 매수 종목 가격 * 매수 주식 수
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User temp = (User) obj;
			return userName.equals(temp.userName);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return userName.hashCode();
	}

}