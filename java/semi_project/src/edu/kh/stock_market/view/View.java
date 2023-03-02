package edu.kh.stock_market.view;

import java.util.Scanner;

public class View {
	
	Scanner scan = new Scanner(System.in);

	public void mainView() {
		
		int menuChoice;
		
		System.out.println("============ 일개미 주식 거래소에 오신 것을 환영합니다!! ============");
		System.out.println();
		System.out.println("1. 거래소 입장하기");
		System.out.println("2. 규칙 설명");
		System.out.println("3. 게임 종료");
		System.out.println();
		System.out.println("==========================================================");
		
		do{
			
			System.out.print("실행하고자 하는 메뉴를 입력해주세요 ▶ ");
			menuChoice = scan.nextInt();

		}while(menuChoice<1 || menuChoice>3);
		
		
		switch(menuChoice) {
		
		case 1 : break;
		case 2 : break;
		case 3 : System.out.println("게임을 종료합니다."); return;
		}
		
	}
	
	
	public void ruleView() {
		
		
		
	}
	
}
