package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;

public class Service {
	
	private Stock[] stockArr = new Stock[10];
	
	public Service() {
		stockArr[0] = new Stock("A 엔터", 20000, 10, 1);
		stockArr[1] = new Stock("B 반도체", 3000, 10, 1);
		stockArr[2] = new Stock("C 금융", 60000, 10, 1);
		stockArr[3] = new Stock("D 화학", 300000, 10, 1);
		stockArr[4] = new Stock("E 바이오", 50000, 10, 1);
		stockArr[5] = new Stock("F 식품", 2000, 10, 1);
		stockArr[6] = new Stock("G 식품", 30000, 10, 1);
		stockArr[7] = new Stock("H 자동차", 100000, 10, 1);
		stockArr[8] = new Stock("I 건설", 40000, 10, 1);
		stockArr[9] = new Stock("J 출판", 250000, 10, 1);
	}
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
	// 반환형?
	public double displayService() { // 종목-상승,하락률
		
		
		
		
		
		
		return 0;
		
	}

	public String[] userRandomTurn() { //반환값?
		
		return null;
		
	}
	
	
	
	public String[] buyService() {
		return null;
		
	}
	
	public String[] sellService() {
		return null;
	}

}
