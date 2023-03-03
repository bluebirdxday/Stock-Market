package edu.kh.stock_market.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kh.stock_market.dto.User;

public class Service {

	private Set<User> userSet = null;
	
	
	public Service() {
		
		userSet = new HashSet<>();
	}
	
	
	
	/** 사용자 등록
	 * @param userName 사용자 닉네임
	 * @return userList 사용자 리스트
	 */
	public Set<User> registerUserService(String userName) {
		
		User user = new User(userName);
		userSet.add(user);
		
		return userSet;
		
	}
	

	
	/** 정보 입찰 경매
	 * 입찰가 비교해서 최고 금액 낙찰
	 */
	public void auctionService() {
		
		
	}
	
	
	
	
}
