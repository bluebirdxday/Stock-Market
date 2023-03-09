package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;

public class Service {
	private static final int MIN_CHANGE_PERCENT = -10;
	private static final int MAX_CHANGE_PERCENT = 10;
	List<User> users = new ArrayList<>();

	public Service() {}
	
	// 사용자 등록
	public List<User> registerUserService(String name) {
		User user = new User(name);
		users.add(user);
		return users;
	}

	// 주식정보 초기화
	public List<Stock> initStocks() {
		List<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("한미약품", 1000));
		stocks.add(new Stock("삼성바이오로직스", 1500));
		stocks.add(new Stock("대한항공", 1000));
		stocks.add(new Stock("모두투어", 500));
		stocks.add(new Stock("테슬라", 2500));
		stocks.add(new Stock("MS soft", 1000));
		stocks.add(new Stock("현대모비스", 1500));
		stocks.add(new Stock("기아", 1000));
		stocks.add(new Stock("SM", 500));
		stocks.add(new Stock("하이브", 1500));
		stocks.add(new Stock("DB하이텍", 500));
		stocks.add(new Stock("SK 하이닉스", 2000));
		stocks.add(new Stock("애플", 2000));
		stocks.add(new Stock("삼성전자", 2500));
		stocks.add(new Stock("KB 금융", 1000));
		stocks.add(new Stock("미래에셋대우 증권", 500));
		stocks.add(new Stock("현대건설", 500));
		stocks.add(new Stock("LH", 1500));
		stocks.add(new Stock("한화손해보험", 1000));
		stocks.add(new Stock("DB손해보험", 2000));
		stocks.add(new Stock("코스모화학", 2500));
		stocks.add(new Stock("SK종합화학", 1500));
		stocks.add(new Stock("CJ", 1000));
		stocks.add(new Stock("오뚜기", 500));
		stocks.add(new Stock("아모레퍼시픽", 500));
		stocks.add(new Stock("LG 생활건강", 1000));
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
}