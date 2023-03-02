package edu.kh.stock_market.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.service.Service;

public class View {
	User user  = new User();
	Stock stock  = new Stock();
	
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Stock> stocks = new ArrayList<Stock>();
	
	
	private Scanner sc = new Scanner(System.in);
	private Service service = new Service();
	
	public void displayMenu() {
		
		int input = 0;

		do {
			try {
				System.out.println("일개미 주식 거래소에 오신 것을 환영합니다!!");
				System.out.println("1. 거래소 입장");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
			

				System.out.print("메뉴 선택 >> ");

				input = sc.nextInt();
				sc.nextLine();

				System.out.println();

				switch (input) {

				case 1:
					registerUserInfo();
					break;
				case 2:
					ruleView();
					break;
			
				case 3:
					System.out.println("[ 프로그램 종료 ]");
					break;
				default:
					System.out.println("[ 잘못 입력하셨습니다. ] ");

				}

			} catch (InputMismatchException e) {
				System.out.println("[ 잘못된 형식의 입력입니다.] ");
				sc.nextLine();
				input = -1;
			}

			System.out.println();
		} while (input != 0);
	}	
		
		
	
	
	// 1. 메인화면
	public void mainView() {
		
	}
	
	// 2. 규칙 설명
	public void ruleView() {
		System.out.println("[ 규 칙 설 명 ] ");
		System.out.println("◉ 총 20턴(오전 오후 1턴 씩, 10일 동안 진행), 20턴 후 최종적으로 가장 많은 금액 보유자 승리합니다.");
		System.out.println("◉ n명이 게임 시작 후 1주당 1만원 주식을 거래할 수 있습니다.");
		System.out.println("◉ 주식 가격은 구매 중에도 실시간으로 변경됩니다.");
		System.out.println("◉ 주가는 상황에 따라 수시로 변동됩니다.(퍼센트)\n"
				+ "  -(상승/하락은 10% 내외로)");
		System.out.println("◉ 턴 마다 매수, 매도, 패스를  선택 할 수 있습니다.\n"
				+ "  - 매수, 매도는 각 1번씩만 가능합니다.(총 2회)");
		System.out.println("◉ 주식 수는 모두 10주씩으로 한정되어 있으며 종목 당 주식은 게임 중 유저 수x10주씩 발행됩니다.");
		System.out.println("◉ 1턴당 한 유저의 매수 가능한 주식 수는 10주씩 한정되어 있습니다.");
		System.out.println("◉ (코스피-코스닥 지수 표기(장이 좋을지 나쁠지 랜덤))");
		System.out.println("◉ 주식장과 관련된 정보 입찰이 격일로 진행됩니다.\n"
				+ "  가장 큰 금액을 배팅한 유저가 정보를 낙찰 받으나, 정보는 상폐 종목/상승 종목/하락 종목/되도 않는 정보 중\n  랜덤으로 제공됩니다.(유저 선택 불가)");
		System.out.println("◉ 이벤트가 발생하는 날이 있습니다.\n"
				+ "  이벤트 발생일과 시간은 모두 랜덤입니다.\n"
				+ "  이벤트 발생 시 해당 턴에서 첫번째 유저만 이벤트 내용을 확인 할 수 있으며, 해당 유저는 5주 이내로 한 종목을 매수할 수 있습니다.\n  -(이벤트는 횟수가 정해져 있고(5회) 랜덤으로 발생함)\n"
				+ "  -(이벤트 발생 타이밍도 랜덤)");
		System.out.println("◉ 이벤트에 따라 주가가 변동됩니다.\n"
				+ "  -(이벤트마다 상승률(or 하락률) 지정)\n"
				+ "  -(이벤트 발생 시 한 사람 당 매수할 수 있는 주식 수 제한 두기)");
		
		
	}
	
	// 3.사용자 등록
	public void registerUserInfo() {
		System.out.println("[ 사용자 등록 ]");
		System.out.print("게임 참여 인원의 수를 입력하십시오 : ");
		int userNum = sc.nextInt();
		
		
		
		for(int i=0; i< userNum ; i++) {
			System.out.printf("User %d의 아이디를 입력하세요 : ",i+1);
			String name = sc.next();
			User user = new User(name);
			users.add(user);
		
		}
		
		
		System.out.println();
		System.out.printf("%d명의 User가 등록되었습니다.", userNum);
		System.out.println();
		System.out.print("\n등록된 User : ");
		for(User user : users) {
			System.out.print(user.getUserName()+"님 ");
		}
		System.out.print("등록이 완료되었습니다.");
		System.out.println("\n----------------------------------");
	
		
		Collections.shuffle(users); // 이름 리스트를 섞는다.
        for(int i=0; i< users.size();i++) {
        	User user = users.get(i);
        	System.out.println((i+1)+"번째 : "+user.getUserName()+"님");
        }
        stockDisplay();
        userInfoAndSelectStockOption();
	}
		
		
	
	
	// 4. 정보 경매
	public void informationAuction() {
		
	}
	
	// 5. 낙찰된 정보의 상세 내용
	public void informationView() {
		
	}
	
	// 6. 종목 전광판
	public void stockDisplay() {
		System.out.println("———————————————————전광판———————————————————");
		System.out.println(user.getDay()+" 일 ");
		for(int i = 0; i<service.getStockList().size(); i++) {
			System.out.println((i+1)+"."+service.getStockList().get(i).getStockName()+" : "
								+service.getStockList().get(i).getStockPrice()+"원");
		}
		System.out.println("——————————————————————————————————————");
		System.out.print("종목 선택 : ");
		int input = sc.nextInt();
		
		
		
	}
	
	// 7. 사용자 정보 및 종목 매수/매도/패스 선택
	public void userInfoAndSelectStockOption() {
		User user  = new User();
		System.out.println();
		System.out.println("┏━━━━━━━┓");
		System.out.println("│  "+user.getDay()+" 일 ");
		System.out.println("┗━━━━━━━┛");
		System.out.println();
		
		for(int i=0; i< users.size() ;i++) {
			System.out.println("---"+users.get(i).toString()+"님의 정보---");
			System.out.println(users.get(i).toString());
			System.out.println();
			System.out.println("보유 현금 :"+user.getCashHoldings()+"원");
			System.out.println("보유 주식 :"+user.getStocks()+"주");
			System.out.println();
			System.out.println("1. 매수");
			System.out.println("2. 매도");
			System.out.println("3. pass");
			System.out.println("4. cancel");
			System.out.println();
			System.out.print("실행할 메뉴를 선택하세요 : ");
			int input = sc.nextInt();
			
			switch(input){
				case 1: buyView(); break;
				case 2: sellView(); break;
				case 3: continue;
				case 4: break;
				default: System.out.println("[ 잘못 입력하셨습니다. ] ");
			
			}
			i++;
		}
		
		
	}
	
	// 8. 매수 페이지
	public void buyView() {
		
	}
	
	// 9. 매도 페이지
	public void sellView() {
		
	}
	
	// 10. 이벤트 발생
	public void evernView() {
		
	}
	
	// 11. 순위
	public void rankingView() {
		
	}
	
}
