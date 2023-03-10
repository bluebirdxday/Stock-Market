package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
		
		if((int)(Math.random()*2) == 0) {	
			informationAuction();   // 랜덤으로 나타나게 하기
		}
		
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

	/** 정보 경매
	 * 
	 */
	public void informationAuction() {
		
		final int MAX_BIDPRICE = 50000;  // 최대 금액
		int bidPrice = 1000; // 입찰가
		int quotePrice;  // 회원 제시가
		User finalBidder = null;   // 최종 낙찰자
		
		Scanner sc = new Scanner(System.in);
		
		Iterator<User> userIterator;
			//users 리스트 사용하기
		
		Map<User, Integer> bidPriceMap = new HashMap<>(); // 입찰가 Map(key : userName, value : bidPrice)
		
		System.out.println();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.printf("%d-%d-%d\n", year, month, day);
		System.out.println("[정보 경매를 시작합니다]");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		
		System.out.println();
		
		System.out.println("원하는 금액을 제시해주세요. (최대 금액 : 50,000원) ");
		System.out.println("패스하시려면 (0)를 입력해주세요.");
		
		System.out.println();
		System.out.println("현재 입찰가 : " + bidPrice + "원");
		System.out.println();
		
		
		for(int i=0; i<2; i++) {
				
			userIterator = users.iterator();  // 회원 리스트 순회를 위한 iterator
			
			while(userIterator.hasNext()) {
				
				User user = userIterator.next();				
			
				try {

					while(true) {

						System.out.print(user.getUserName() + " ▶ ");
						quotePrice = sc.nextInt();
						sc.nextLine();
						
						if(quotePrice==0) {
							userIterator.remove();
							break;
						}
						
				     	else if(quotePrice<bidPrice) System.out.println("[X] 더 높은 금액을 제시해주세요 [X]"); // 회원 제시가 < 초기 입찰가
						
						else if(quotePrice>MAX_BIDPRICE) System.out.println("[X] 입찰 가능한 금액을 초과합니다 [X]");  // 회원 제시가 > 최대 금액

						else {
							bidPriceMap.put(user, quotePrice);
							break;
						}
						
					}
							
				
				}catch(InputMismatchException e) {
					
					System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
					System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
				
				}catch(Exception e) {
					
					System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
				}
			}
		
			if(bidPriceMap.isEmpty()) {
				System.out.println("낙찰자가 존재하지 않습니다!");
				System.out.println();
				break;
			}
		
			
			finalBidder = service.auctionService(bidPriceMap);
			System.out.println("---------------------------------");
			
		}

		if(finalBidder!=null) {

			System.out.println();
			System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님 ]");
			System.out.println(" 축하드립니다!");
			System.out.println();
			
		}else {
			
			System.out.println("[낙찰자가 존재하지 않습니다!]");
			System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		}
		
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
				

		

				while (true) {
					
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
				            sellView(user);
				            break;
				        case 3:
				            System.out.println("[ 아직 매수 / 매도가 가능합니다. 패스하시겠습니까? ]");
				            System.out.println("1. 패스        2. 돌아가기");
				            System.out.println();
				            System.out.print("실행할 메뉴를 선택하세요 ▶ ");
				            
				            int input2 = sc.nextInt();
				            
				            if(input2 == 1) break;
				            
				            if(input2 == 2) {
				            	System.out.println();
				            	System.out.println();
				            	continue;
				            }
				            	
				            
				        default:
				            System.out.println("[ 잘못 입력하셨습니다 ] ");
				            continue;
				    }
				    
				    break;
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
		
		int input=0;
		Stock buyStock;

		/* 예외상황.
		 * 1. 숫자 외 입력
		 * 2. 매수 종목 번호 범위 초과 
		 * */
		while(true) {
			try {
				System.out.print("매수 종목 번호 입력 : ");
				input = sc.nextInt();
				// 구매할 종목 정보 대입
				buyStock = stocks.get(input-1);
				break;
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
		}

		/* 예외상황.
		 * 1. 숫자 외 입력
		 * 2. input*buyStock.getStockPrice()가 user.getCashHoldings()를 초과하는 경우   
		 * */
		while (true) {
			try {
				System.out.println();
				System.out.println(buyStock.getStockName() + "의 현재 가격은 " + buyStock.getStockPrice() + "원 입니다.");
				System.out.print("몇 주 구매하시겠습니까? ");
				input = sc.nextInt();
				sc.nextLine();
				if (input*buyStock.getStockPrice() > user.getCashHoldings()) {
					System.out.println("보유 현금이 부족합니다.");
					continue;
				}
				
				break;
				
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
		}
		user.buyStock(buyStock,input);
	}

	
	
	// 매도 페이지
	public void sellView(User user) {

		int input=0;
		boolean sellSuccess;
		Stock sellStock;

		/* 예외상황.
		 * 1. 숫자 외 입력
		 * 2. 매도 종목 번호 범위 초과 
		 * */
		while(true) {
			
			
			
			try {
				System.out.print("매도 종목 번호 입력 : ");
				input = sc.nextInt();
				
				// 매도 종목 번호 입력 시 갖고 있는 종목이 아니라면 매도할 수 없도록
				if(service.findStock(user, stocks.get(input-1)) != null) {
					
					// 매도할 종목 정보 대입
					sellStock = stocks.get(input-1);
					break;
					
					
				}else {
				
					System.out.println("현재 보유하고 있는 종목이 아니므로 매도 불가합니다");
					return;
				}
				
				
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
			
			
		}
	
		
		
		/* 예외상황.
		 * 1. 숫자 외 입력
		 * 2. input이 user의 userStock 리스트 내 stock 개수를 초과하는 경우   
		 * */
		while (true) {
			try {
				System.out.println(sellStock.getStockName() + "의 현재 가격은 " + sellStock.getStockPrice() + "원 입니다.");
				System.out.print("몇 주 판매하시겠습니까? ");
				input = sc.nextInt();
				sc.nextLine();
				if (input > service.findStock(user, sellStock).getStockCount()) {
					System.out.println("입력하신 값보다 보유하고 있는 주식 수가 부족합니다.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
		}
		
		user.sellStock(sellStock,input, service.findStock(user, sellStock));
		
	}
	

	// 이벤트 발생
	public void evernView() {
	}

	// 순위
	public void rankingView() {

	}

}