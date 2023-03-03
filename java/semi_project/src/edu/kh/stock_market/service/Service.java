package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.stock_market.dto.User;

public class Service {

	private List<User> userList;
	
	
	public Service() {
		
		userList = new ArrayList<>();
	}
	
	
	
	/** 사용자 등록
	 * @param userName 사용자 닉네임
	 * @return userList 사용자 리스트
	 */
	public List<User> registerUserService(String userName) {
		
		User user = new User(userName);
		userList.add(user);
		
		return userList;
		
	}
	
	
	
	
}
