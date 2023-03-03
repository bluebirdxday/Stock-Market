package edu.kh.stock_market.view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.service.Service;

public class View {
	
	private Scanner sc = new Scanner(System.in);
	private Service service = new Service();
	
	public void displayMenu() {
		int input = 0;
		
		do {
			try {
				System.out.println("일개미 주식 거래소에 오신 것을 환영합니다!!*\n");
				System.out.println("1. 거래소 입장하기");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
				System.out.println();
				
				System.out.print("메뉴 선택>> ");
				
				int selectMenu = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(selectMenu) {
				case 1: registerUserService(); break;
				case 2: ruleDescripion(); break;
				case 3: System.out.println("[게임 종료]"); break;
				default : System.out.println("[잘못 입력하셨습니다.]");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("[잘못된 형식의 입력입니다.]");
				sc.nextLine();
				input = -1;
			}
			System.out.println();
			
		}while(input!=0);
	}
	

	/**
	 * 거래소 입장 (= 사용자 등록)
	 * */
	private void registerUserService() {
//		int userCount = 0;
//		
//		System.out.println("\n---[사용자 등록하기]---\n");
//		System.out.print("등록할 인원 : ");
//		
//		userCount = scan.nextInt();
//		scan.nextLine();
//
//		for(int i=1; i<userCount+1; i++) {
//			System.out.printf("USER %d : ", i);
//			userSet = service.registerUserService(scan.nextLine());
//		}
//		
//		Iterator<User> iterator = userSet.iterator();
//		
//		int count = 1;
//		
//		for(int i=0; i<userList.size) {
//			
//		}
//
//		
//
//		
	}

	

	/**
	 * 규칙 설명
	 * */
	private void ruleDescripion() {
		System.out.println();
		System.out.println("================================ 기본 규칙 ================================");
		System.out.println();
		System.out.println("[1] 총 20턴을 돌며 최종적으로 가장 많은 자산을 보유한 사람이 승리합니다.");
		System.out.println("[2] 1주당 1만원 주식을 거래할 수 있습니다.");
		System.out.println("[3] 초기 자본은 10만원입니다.");
		System.out.println("[4] 주가는 상황에 따라 수시로 변동됩니다.");
		System.out.println("[5] 턴 마다 매수, 매도, 패스를 선택 할 수 있습니다. 매수, 매도는 각 1번씩만 가능합니다 (총 2회)");
		System.out.println("[6] 모든 종목은 10주로 한정되어 있습니다.");
		System.out.println("[7] 정보 입찰이 격일로 진행됩니다.");
		System.out.println("[8] 이벤트가 발생하는 날이 있으며 이에 따라 주가가 변동됩니다. ");
		System.out.println();
		System.out.println("========================================================================");
		
		System.out.println();
		
		while(true) {
			System.out.println("뒤로가기 [B] ▶ ");
		}
	}
	
	
	private void startGame() {
		System.out.println("현재 입찰가 : 1000원");
		System.out.println();
		
//		System.out.printf("USER %d : ", );
//		if() {
			
		}
	}
}
