package edu.kh.stock_market.view;

import java.util.InputMismatchException;
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
				case 1: entranceGame(); break;
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
	private void entranceGame() {
		System.out.println("\n---[사용자 등록하기]---\n");
		
//		while(true) {
//			if() {
//				
//			}
//			
//			
//		}
//		
//		System.out.println("[닉네임을 입력해주세요]");

		
//		if(service.addUser(user)) {
//			System.out.println("[사용자 정보가 추가되었습니다.]");
//		}
		
	}


	/**
	 * 규칙 설명
	 * */
	private void ruleDescripion() {
		System.out.println("---[기본 규칙 설명]---");
		System.out.println("총 20턴(오전 오후 1턴 씩, 10일 동안 진행), 20턴 후 최종적으로 가장 많은 금액 보유자 승리합니다.");
		System.out.println("n명이 게임 시작 후 1주당 1만원 주식을 거래할 수 있습니다.");
		System.out.println("주식 가격은 구매 중에도 실시간으로 변경됩니다.");
		System.out.println("초기 자본 모두 10만원으로 시작합니다.");
		System.out.println("주가는 상황에 따라 수시로 변동됩니다.(퍼센트)\r\n"
				+ "(상승/하락은 10% 내외로)\n");
		System.out.println("턴 마다 매수, 매도, 패스를  선택 할 수 있습니다.\r\n"
				+ "매수, 매도는 각 1번씩만 가능합니다.(총 2회)\n");
		System.out.println("주식 수는 모두 10주씩으로 한정되어 있으며 \r\n"
				+ "종목 당 주식은 게임 중 유저 수 * 10주씩 발행됩니다.");
		System.out.println("1턴당 한 유저의 매수 가능한 주식 수는 10주씩 한정되어 있습니다.\n");
		System.out.println();
		System.out.println("---[정보 설명]---");
		System.out.println("주식장과 관련된 정보 입찰이 격일로 진행됩니다.\r\n"
				+ "가장 큰 금액을 배팅한 유저가 정보를 낙찰 받으나, 정보는 상폐 종목/상승 종목/하락 종목/되도 않는 정보 중 랜덤으로 제공됩니다.(유저 선택 불가)\n");
		System.out.println();
		System.out.println("---[이벤트 설명]---");
		System.out.println("이벤트가 발생하는 날이 있습니다.\r\n"
				+ "이벤트 발생일과 시간은 모두 랜덤입니다.\r\n"
				+ "이벤트 발생 시 해당 턴에서 첫번째 유저만 이벤트 내용을 확인 할 수 있으며, 해당 유저는 5주 이내로 한 종목을 매수할 수 있습니다.\r\n"
				+ "(이벤트는 횟수가 정해져 있고(5회) 랜덤으로 발생함)\r\n"
				+ "(이벤트 발생 타이밍도 랜덤)");
		System.out.println("이벤트에 따라 주가가 변동됩니다.\r\n"
				+ "(이벤트마다 상승률(or 하락률) 지정)\r\n"
				+ "(이벤트 발생 시 한 사람 당 매수할 수 있는 주식 수 제한 두기)");
		
	}
}
