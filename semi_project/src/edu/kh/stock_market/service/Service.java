package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	
	/** 정보 입찰 경매
	 * 입찰가 비교해서 최고 금액 낙찰
	 * @param map<사용자 객체, 입찰가>
	 * @return 최고 입찰가 사용자 이름 or 최고 입찰 금액이 여러명 존재할 경우 랜덤으로 사용자 하나 선택
	 */
	public User auctionService(Map<User, Integer> map) {
	
	
		Entry<User, Integer> maxEntry = null;
		ArrayList<User> arrayList = new ArrayList<>();
		int count = 0; // 중복 체크 값
		
		
		for(Map.Entry<User, Integer> entry : map.entrySet()) {
				
			if(maxEntry==null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}else if(entry.getValue() == maxEntry.getValue()) {
				arrayList.add(entry.getKey());
				count++;
			}

		}
		
		if(arrayList.isEmpty()) {
			return maxEntry.getKey();
		}else {
			return arrayList.get((int)Math.random()*count);			
		}
		
	}

	
	
	
}