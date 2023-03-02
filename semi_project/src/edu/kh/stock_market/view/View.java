package edu.kh.stock_market.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.stock_market.service.Service;

public class View {
	
	private Scanner sc = new Scanner(System.in);
	private Service service = new Service();

	public void mainView() {
		System.out.println("일개미 주식 거래소에 오신 것을 환영합니다!!");
		
		int input = 0;
		
		do {
			try {
				System.out.println("1. 거래소 입장하기");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
				System.out.println();
				System.out.print("메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1 : registerUserInfo(); break;
				case 2 : ruleView(); break;
				case 3 : System.out.println("게임을 종료합니다.");
				default : System.out.println("잘못 입력하셨습니다.");
				} 
			} catch (InputMismatchException e) {
				System.out.println("입력 형식이 잘못되었습니다.");
				sc.nextLine();
				input = -1;
			} System.out.println();
		} while (input != 0);
	}

	
	// 사용자 등록
	private void registerUserInfo() {
		
		
	}



	// 규칙 설명
	private void ruleView() {
		
		
	}
	
	
	
	
	
	
}
