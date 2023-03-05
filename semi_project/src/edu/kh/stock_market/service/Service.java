package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.stock_market.dto.User;

public class Service {
	
	private Scanner sc = new Scanner(System.in);
	

	public String[] registerUserService(int userNumber) {
		
		String[] user = new String[userNumber];// user 명단만(닉네임만) 저장하는 배열
		
		System.out.println();
		
		System.out.println("[닉네임을 입력해주세요.]");
		for(int i=0; i<userNumber; i++) {
			try {
				System.out.printf("USER %d : ", i+1);
				user[i] = sc.next();
				
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("이미 입력한 인원 수 만큼 닉네임이 설정되었습니다.");
			} 
		} 
		return user;
	}

	public String[] userRandomTurn() { //반환값?
		return null;
		
	}
	
	public double displayService() {
		return 0;
		
	}
	
	public String[] buyService() {
		return null;
		
	}
	
	public String[] sellService() {
		return null;
	}

}
