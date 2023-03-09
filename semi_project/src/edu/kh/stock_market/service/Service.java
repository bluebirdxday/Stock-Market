package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.dto.UserStock;

public class Service {
	
	private static final int MIN_CHANGE_PERCENT = -10;
	private static final int MAX_CHANGE_PERCENT = 10;
	List<User> users = new ArrayList<>();

	public Service() {
	}

	// 주식정보 초기화
	public List<Stock> initStocks() {
		List<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("삼성전자", 1000));
		stocks.add(new Stock("SK하이닉스", 2000));
		stocks.add(new Stock("NAVER", 3000));
		stocks.add(new Stock("현대차", 2500));
		stocks.add(new Stock("삼성바이오로직스", 8000));
		stocks.add(new Stock("LG화학", 15000));
		stocks.add(new Stock("삼성SDI", 4000));
		stocks.add(new Stock("셀트리온", 1500));
		stocks.add(new Stock("카카오", 18000));
		stocks.add(new Stock("기아차", 1000));
		stocks.add(new Stock("POSCO", 2000));
		stocks.add(new Stock("LG전자", 4000));
		stocks.add(new Stock("SK이노베이션", 5000));
		stocks.add(new Stock("삼성생명", 50000));
		stocks.add(new Stock("현대모비스", 2500));
		stocks.add(new Stock("LG생활건강", 3000));
		stocks.add(new Stock("KB금융", 7000));
		stocks.add(new Stock("한국전력", 6000));
		stocks.add(new Stock("SK텔레콤", 4000));
		return stocks;
	}
	

	// 주식가격 업데이트
	public void updatePrice(List<Stock> stocks) {
		for (Stock s : stocks) {
			int changePercent = (int) (Math.random() * (MAX_CHANGE_PERCENT - MIN_CHANGE_PERCENT + 1))
					+ MIN_CHANGE_PERCENT;
			int changeAmount = (int) (s.getStockPrice() * changePercent / 100.0);
			int newPrice = s.getStockPrice() + changeAmount;
			s.setPrevPrice(s.getStockPrice());
			s.setStockPrice(newPrice);
		}
	}
	
	// 사용자 등록
	public List<User> registerUserService(String name) {
		User user = new User(name);
		users.add(user);
		return users;
	}
	
	
	
	/** 날짜 계산
	 * @param month
	 * @param day
	 * @return
	 */
	public boolean calcDay(int month, int day) {
		
		switch(month) {
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10 :
		case 12 :
			if(day==31) 
				return false;
		case 2 :
			if(day==28)
				return false;
		case 4 :
		case 6 :
		case 9 :
		case 11 :
			if(day==30)
				return false;
		}
		
		return true;
	}
	
	
	public List<UserStock> addStock(List<UserStock> userStocks, Stock chosenStock, int buyStockNum) {

		if(userStocks.isEmpty()) {  // 사용자 종목 리스트가 비어있다면 새로 생성해서 추가
			UserStock userStock = new UserStock(chosenStock.getStockName(), buyStockNum);
			userStocks.add(userStock);
		}else{
			
			for(UserStock userStock : userStocks) {
				
				if(userStock.getStockName().equals(chosenStock.getStockName()))  // 매수한 주식종목명과 사용자 종목리스트 내 종목명이 일치하면 주식수만 변경
					userStock.setStockCount(buyStockNum); 
				else {
					UserStock userStock2 = new UserStock(chosenStock.getStockName(), buyStockNum);   // 일치하는 게 없으면 추가
					userStocks.add(userStock2);
				}
			}
		}
		
		return userStocks;

	
	}
	
	
}