package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.dto.UserStock;
import edu.kh.stock_market.service.Service;

public class View {
	private Scanner sc;
	private Service service;
	private List<User> users;
	private List<Stock> stocks;
	private List<UserStock> userStocks;

	LocalDate now = LocalDate.now(); // 현재 날짜 구하기
	int year = now.getYear();
	int month = now.getMonthValue();
	int day = now.getDayOfMonth();

	public View() {
		sc = new Scanner(System.in);
		service = new Service();
		mainView();
	}

	// 메인화면
	public void mainView() {
		int input;
		while (true) {
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
					enterStockExchange();
					break;
				case 2:
					ruleView();
					break;
				case 3:
					System.out.println("[ 프로그램 종료 ]");
					return;
				default:
					System.out.println("[ 잘못 입력하셨습니다. ] ");
				}
			} catch (InputMismatchException e) {
				System.out.println("[ 잘못된 형식의 입력입니다.] ");
				sc.nextLine();
			}
			System.out.println();
		}
	}

	// 거래소 입장
	public void enterStockExchange() {
		registerUserInfo();
		stocks = service.initStocks();
		userInfoAndSelectStockOption();
	}

	// 사용자 등록
	// 사용자 등록
	public void registerUserInfo() {

		System.out.println("[ 사용자 등록 ]");
		System.out.print("게임 참여 인원의 수를 입력하십시오  ▶ ");
		int userNum = sc.nextInt();

		for (int i = 0; i < userNum; i++) {
			System.out.printf("User %d의 아이디를 입력하세요 ▶ ", i + 1);
			users = service.registerUserService(sc.next());
		}
		System.out.println();
		
		
	}

	// 주식정보 출력
	public void disStocks() {
		System.out.println("\n---------- 주식 정보 ----------");
		for (int i = 0; i < stocks.size(); i++) {
			System.out.printf("[%d]  ", i + 1);
			System.out.println(stocks.get(i).getStockInfo());
		}
	}

	// 현재 유저정보 출력
	public void disUsers() {
	}

	// 규칙 설명
	public void ruleView() {

		Scanner scan = new Scanner(System.in);
		char backInput;

		System.out.println();
		System.out.println("================================ 기본 규칙 ================================");
		System.out.println();
		System.out.println("[1] 10일 동안 오전,오후 총 20턴 거래를 돌며 최종적으로 가장 많은 자산을 보유한 사람이 승리합니다.");
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

		while (true) {

			System.out.print("뒤로가기 [B] ▶ ");
			backInput = scan.nextLine().toUpperCase().charAt(0);

			if (backInput == 'B')
				break;

			else {

				System.out.println("올바른 키를 입력해주세요.");
				System.out.println();
			}
		}

	}

	// 정보 경매
	public void informationAuction() {

	}

	// 낙찰된 정보의 상세 내용
	public void informationView() {

	}

	public void userInfoAndSelectStockOption() {
		System.out.println();
		System.out.println();
		User user = null;

		for (int j = 1; j <= 10; j++) {

			int amOrPm = j % 2;

			if (j != 1)
				service.updatePrice(stocks);
			
			System.out.printf("\n\n %d년 %d월 %d일   %s", year, month, day, (amOrPm == 1 ? "오전" : "오후"));

			for (int i = 0; i < users.size(); i++) {

				
//				service.setProperty(user, stocks);      
				
				disStocks();
				user = users.get(i);
				userStocks = user.getUserStockList();

				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println("[ " + user.getUserName() + "님의 자산 ]");
				System.out.println("총 평가 금액  : " + user.getProperty() + "원");
				System.out.println("전일 대비 ");

			
				if (userStocks.size() != 0) {

					System.out.println();
					System.out.println("===========================================================");
					System.out.println("종목명\t\t평단\t\t주식수\t\t현재금액\t\t수익률");
					System.out.println("===========================================================");

					
					Iterator<UserStock> iterator = userStocks.iterator();
					
					while(iterator.hasNext()) {
						UserStock userStock = iterator.next();
						
						String stockName = userStock.getStockName();
						if(!userStock.getStockName().equals("삼성바이오로직스")) stockName+="\t";
						System.out.printf("%s\t%d\t\t%d\n", stockName, userStock.getAveragePrice(),userStock.getStockCount());
					}


					System.out.println("===========================================================");
				}
				

				System.out.println();
				System.out.println("1. 매수");
				System.out.println("2. 매도");
				System.out.println("3. pass");
				System.out.println();
				System.out.print("실행할 메뉴를 선택하세요 ▶ ");
				int input = sc.nextInt();

				switch (input) {
				case 1:
					buyView(user);
					break;
				case 2:
					sellView();
					break;
				case 3:
					System.out.println("[ 아직 매수 / 매도가 가능합니다. 패스하시겠습니까? ]");
					System.out.println("1. 패스        2. 돌아가기");
					System.out.println();
					System.out.print("실행할 메뉴를 선택하세요 ▶ ");
					int input2 = sc.nextInt();
					if (input2 == 1)
						continue;
				default:
					System.out.println("[ 잘못 입력하셨습니다 ] ");
				}
			}

			if (service.calcDay(month, day)) {
				day++;
			} else {
				month++;
				day = 1;
			}
		}
	}
	
	public void buyView(User user) {
		System.out.print("매수 종목 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		Stock buyStock = stocks.get(input-1);
		System.out.println(buyStock.getStockName() + "의 현재 가격은 "+ buyStock.getStockPrice()+"원 입니다.");
		System.out.print("몇 주 구매하시겠습니까? ");
		input = sc.nextInt();
		user.buyStock(buyStock,input);
	}

	// 매수 페이지
//	public void buyView(User user, List<UserStock> userStocks) {
//
//		System.out.print("매수할 종목의 번호를 입력해주세요 ▶ ");
//
//		int buyStockNum = sc.nextInt();
//		sc.nextLine();
//
//		System.out.println();
//		System.out.println();
//
//		Stock chosenStock = stocks.get(buyStockNum - 1);
//
//		System.out.println(chosenStock.getStockName());
//
//		for (int i = 1; i <= 10; i++) {
//			System.out.printf("%d주 가격 : %d\n", i, i * chosenStock.getStockPrice());
//		}
//
//		while (true) {
//
//			System.out.println();
//			System.out.print("매수할 주식 수  ▶ ");
//
//			try {
//
//				buyStockNum = sc.nextInt();
//				sc.nextLine();
//
//				if (buyStockNum == 0) {
//					System.out.println("입력할 수 없는 값입니다. 다음 턴으로 넘어갑니다.");
//					break;
//				}
//
//			} catch (Exception e) {
//
//				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
//
//			}
//
//			// if(chosenStock 가격 > 유저 보유 현금 ) 현금 부족, 다시 입력
//			// else 구매 완료 service.userBuyStock 만들기 => user의 주식arrayList에 매수한 주식 정보 추가
//
//			if (user.getCashHoldings() < chosenStock.getStockPrice() * buyStockNum)
//				System.out.println("현금이 부족합니다. 다시 입력해주세요.");
//			
//			else {
//
//				
//				userStocks = service.addStock(userStocks, chosenStock, buyStockNum);
//				
//				user.setUserStockList(userStocks);
//				
//
//				int totalPrice = chosenStock.getStockPrice() * buyStockNum; // 매수 종목 가격 * 매수 주식 수
//
//				user.updateProperty(totalPrice); // 총 자산과 보유 현금 정보 업데이트
//
//				System.out.println();
//				System.out.println("[매수가 완료되었습니다.]");
//				System.out.println("매입 금액 : " + totalPrice);
//				
//				
//				int index = -1;
//
//				for(int i=0; i<userStocks.size(); i++) {
//					
//					if(userStocks.get(i).getStockName().equals(chosenStock.getStockName()))
//						index = i;
//				}
//
//				System.out.println(chosenStock.getStockName() + " 보유 주식 수 : " + userStocks.get(index).getStockCount());
//				// ?? 사용자가 기존에 보유한 주식 수 + 매수한 주식 수 인지 || 사용자가 방금 매수한 주식 수만 말하는 건지 ?? -- 일단 전자로 함
//				
//				break;
//			}
//		}
//
//	}

	
	// 매도 페이지
	public void sellView() {

		System.out.println("매도할 종목의 번호를 입력해주세요 ▶ ");

		System.out.println();
	}

	// 이벤트 발생
	public void evernView() {
	}

	// 순위
	public void rankingView() {

	}

}