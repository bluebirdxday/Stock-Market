package edu.kh.stock_market.service;

import java.util.HashSet;
import java.util.Set;

import edu.kh.stock_market.dto.User;

public class Service {

	private Set<User> userSet = null;
	
	public Service() {
		userSet = new HashSet<>();
	}
	
	/** 사용자 등록
	 * @param userName
	 * @return
	 */
	public Set<User> registerUserService(String userName){
		User user = new User(userName);
		userSet.add(user);
		
		return userSet;
	}


}
