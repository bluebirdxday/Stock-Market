package edu.kh.stock_market.dto;

import java.util.ArrayList;
import java.util.List;


public class User {
	
	public static final int cash = 100000; // 시작시 현금
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

	

	// 총 자산 업데이트 (주식 가격 변동 시)
	public void updateProperty(int totalPrice) {
//		cashHoldings -= totalPrice; 
//		property = cashHoldings + totalPrice; 
		property = cashHoldings + totalPrice;  // 총 자산  = 현금 보유량 + 총 주식 평가 가격
	}
	
	// 주식 구매
	public void buyStock(Stock buyStock, int num) {
		// 구매할 주식이 이미 보유중인지 비교
		for(UserStock s : userStockList){
			// 구매할 주식의 이름과 보유중식 주식의 이름 중 같은 것이 있는 지 비교
			// 같은 것이 있다면, 처리해주고 메서드 종료.
			if(s.getStockName().equals(buyStock.getStockName())){
				// 평단가 재설정.  (평단가*보유수량+현재가격*구매수량)/(보유수량+구매수량)
				s.setAveragePrice((
								s.getAveragePrice()*s.getStockCount()
								+buyStock.getStockPrice() * num)
								/ (s.getStockCount() + num));
				// 보유주식 수량에 수량 추가
				s.setStockCount(s.getStockCount()+num);
				// 현재 가격 * 구매 수량만큼 보유현금 차감
				cashHoldings -= buyStock.getStockPrice() * num;
				return;
			}
		}
		// 같은 것이 없었다면 새로 추가.  유저주식 생성(구매주식이름, 구매주식수량, 구매주식의 현재가격을 평단가에 대입)
		userStockList.add(new UserStock(buyStock.getStockName(),num,buyStock.getStockPrice()));
		// 현재 가격 * 구매 수량만큼 보유현금 차감
		cashHoldings -= buyStock.getStockPrice() * num;
	}
	
	

	// sellStock 다시 작성하기
	public void sellStock(Stock sellStock, int num, UserStock userStock) {
		for(UserStock s : userStockList){
			// 매도할 주식의 이름과 보유중식 주식의 이름 중 같은 것이 있는 지 비교
			// 같은 것이 있다면, 처리해주고 메서드 종료.
			if(s.getStockName().equals(sellStock.getStockName())){
				
				// 평단가 재설정.  (평단가*보유수량-현재가격*매도수량)/(보유수량-매도수량)
				
				if(s.getStockCount()-num!=0) {
					
					s.setAveragePrice((
							s.getAveragePrice()*s.getStockCount()
							-sellStock.getStockPrice() * num)
							/ (s.getStockCount() - num));
				}else {
					
					userStockList.remove(userStock);
				}
					
							
				// 보유주식 수량에 매도한 주식 수량 차감
				s.setStockCount(s.getStockCount()-num);
				// 현재 가격 * 매도 수량만큼 보유현금 증가
				cashHoldings += sellStock.getStockPrice() * num;
				return;
			}
		}
	
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