package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.stock_market.dto.User;

public class Service {

	private List<User> userList = new ArrayList<User>();

	
	/** userList에 사용자 추가
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		return userList.add(user);
	}
}
