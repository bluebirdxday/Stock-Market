package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import edu.kh.stock_market.data.Information;
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
	private List<Information> infos; // 정보 배열
	List<Map<Stock, Integer>> incDecList = new ArrayList<Map<Stock, Integer>>(); // 정보 경매 상승,하락 종목들

	LocalDate now = LocalDate.now(); // 현재 날짜 구하기
	int year = now.getYear();
	int month = now.getMonthValue();
	int day = now.getDayOfMonth();
	
	int nextDay = 0;  // 정보 경매 등락률 다음 오전/오후 타임에 적용
	
	Random random = new Random();

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
		infos = service.initInfos();     // 서비스에서 정보 생성 초기화
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
		System.out.println("\n------------- 주식 정보 -------------");
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
			backInput = sc.nextLine().toUpperCase().charAt(0);

			if (backInput == 'B')
				break;

			else {

				System.out.println("올바른 키를 입력해주세요.");
				System.out.println();
			}
		}

	}


	public void userInfoAndSelectStockOption() {
		System.out.println();
		System.out.println();
		User user = null;
		User finalBidder=null;
		

		for (int j = 1; j <= 20; j++) {

			int amOrPm = j % 2;
			
			
			if((int)(Math.random()*2) == 0)
				finalBidder = informationAuction();
			
			
			if (j != 1)
				service.updatePrice(stocks); 
			
			
			if(finalBidder!=null) {
				
				InformaionView(finalBidder);
				finalBidder=null;
			}
				

			
			if(day==nextDay) {
				service.applyIncDecRateToStock(stocks, incDecList);
			}
			
				
			for (int i=0; i < users.size(); i++) {
				
				System.out.printf("\n\n %d년 %d월 %d일   %s", year, month, day, (amOrPm == 1 ? "오전" : "오후"));
			
				disStocks();
				user = users.get(i);
				userStocks = user.getUserStockList();
				
				
				int totalPrice = 0; // 사용자 모든 보유 주식 총 평가 가격
				
				for(Stock stock : stocks) { 
					
					// 주식 리스트를 돌면서 사용자 보유 주식 리스트와 일치하는 종목이 있는지 확인
					UserStock userStock = service.findStock(user, stock);   // 일치하는 종목 찾음
					if(userStock!=null) {
						totalPrice += userStock.getStockCount() * stock.getStockPrice();
					} // 주식 평가 가격  = 주식 평가 가격 + 사용자 보유 주식 개수 * 주식 종목 현재 가격
				}
				
							
				
				user.updateProperty(totalPrice);  
				

				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println("[ " + user.getUserName() + "님의 자산 ]");
				System.out.println("총 자산  : " + user.getProperty() + "원");
				System.out.println("현   금 : " + user.getCashHoldings() + "원");
				
					
				
				Iterator<UserStock> ir = userStocks.iterator();
				int currentTotalStockPrcie=0;
				int purchaseTotalAmount=0;
				int totalGainsAndlosses=0;
				int totalAveragePrice=0; // 평단의 합
				
				while(ir.hasNext()) {
					UserStock userStock = ir.next();
										
					currentTotalStockPrcie += service.findUserStock(user, userStock, stocks).getStockPrice()*userStock.getStockCount();  // 현재주가
					purchaseTotalAmount += userStock.getAveragePrice()*userStock.getStockCount(); // 매입금액
					totalGainsAndlosses += currentTotalStockPrcie-purchaseTotalAmount; // 평가손익
					totalAveragePrice += userStock.getAveragePrice()*userStock.getStockCount(); // 평단의 합
				}
				
				System.out.print("평가손익 : ");
				if(totalGainsAndlosses>0)
					System.out.print("+ ");
				System.out.println(totalGainsAndlosses + "원");
				
	
				
				double totalReturn;  // 총 수익률
				
				System.out.print("총 수익률 : ");
				
				if(totalAveragePrice==0)
					totalReturn = 0;
				else {
					totalReturn = (double)totalGainsAndlosses/totalAveragePrice*100;
				}
					
				if(totalReturn>0.0)
					System.out.print("+ ");
				
				System.out.printf("%.2f%%\n", totalReturn);

			
				if (userStocks.size() != 0) {

					System.out.println();
					System.out.println("==============================================================================================================");
					System.out.println("종목명\t\t평단\t\t보유수량\t\t매입금액\t\t현재주가\t\t평가손익\t\t수익률");
					System.out.println("==============================================================================================================");

					
					Iterator<UserStock> iterator = userStocks.iterator();
					int currentStockPrcie=0;  // 현재주가
					int purchaseAmount=0;  // 매입금액
					int gainsAndlosses=0; // 평가손익
					
					while(iterator.hasNext()) {
						UserStock userStock = iterator.next();
						
						String stockName = userStock.getStockName();
						
						//현재의주가 *보유수를 분모로 두고 현재주가 * 보유수 - 당시주가* 보유수를 빼고 이를 100으로 곱하면 수익이 나오죠 
						// 
						
						currentStockPrcie = service.findUserStock(user, userStock, stocks).getStockPrice()*userStock.getStockCount();  // 현재주가
						purchaseAmount = userStock.getAveragePrice()*userStock.getStockCount(); // 매입금액
						gainsAndlosses = currentStockPrcie-purchaseAmount; // 평가손익
						
						if(!userStock.getStockName().equals("삼성바이오로직스")) stockName+="\t";
						System.out.printf("%s\t%d\t\t%d\t\t%d\t\t%d\t\t", stockName, userStock.getAveragePrice(),  // 종목명, 평단
								userStock.getStockCount(), purchaseAmount, currentStockPrcie); // 보유수량, 매입금액, 현재 주가
						
						if(gainsAndlosses>0)
							System.out.print("+");
						System.out.print(gainsAndlosses + "\t\t");
						// 평가 손익 = 현재주가 - 매입금액
						
						if((double)purchaseAmount/gainsAndlosses>0.0)
							System.out.print("+");
						System.out.printf("%.2f%%\n" , (double)gainsAndlosses/purchaseAmount*100); // 수익률 = 매입금액(=투자원금)/손익*100
						
						
					}

					System.out.println("==============================================================================================================");

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
				        	if(!userStocks.isEmpty()) sellView(user);
				        	else {
				        		System.out.println("현재 보유하고 있는 주식이 없습니다! 다른 메뉴를 선택해주세요");
				        		System.out.println("-----------------------------------");
				        		continue;
				        	}
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
			
			nextDay = day + 1;
			

			if(amOrPm!=1) {
	
				if (service.calcDay(month, day)) {
					day++;
				} else {
					month++;
					day = 1;
				}
				
			}
			
		}
	}
	
	
	
	/** 정보 경매
	 * 
	 */
	public User informationAuction() {
		
		final int MAX_BIDPRICE = 50000;  // 최대 금액
		int bidPrice = 1000; // 입찰가
		int quotePrice=0;  // 회원 제시가
		User finalBidder = null;   // 최종 낙찰자
				
		
//		Map<User, Integer> bidPriceMap = new HashMap<>(); // 입찰가 Map(key : userName, value : bidPrice)
		
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
				
			for(int k=0; k<users.size(); k++) {
				
				try {

					while(true) {

						System.out.print(users.get(k).getUserName() + " ▶ ");
						quotePrice = sc.nextInt();
						sc.nextLine();
						
						if(quotePrice==0) {
							break;
						}
						
				     	else if(quotePrice<bidPrice) System.out.println("[X] 더 높은 금액을 제시해주세요 [X]"); // 회원 제시가 < 현재 입찰가
						
						else if(quotePrice>MAX_BIDPRICE) System.out.println("[X] 입찰 가능한 금액을 초과합니다 [X]");  // 회원 제시가 > 최대 금액

						else {
							bidPrice = quotePrice;
							//bidPriceMap.put(users.get(k), quotePrice);
							finalBidder = users.get(k);
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
			
			if(bidPrice==1000) {
				System.out.println("");
				System.out.println("낙찰자가 존재하지 않습니다!");
				System.out.println();
				break;
			}
			
			
			System.out.println("---------------------------------");
			
		
//			if(bidPriceMap.isEmpty()) {
//				System.out.println("낙찰자가 존재하지 않습니다!");
//				System.out.println();
//				break;
//				
//			}else {
//				
//				finalBidder = service.auctionService(bidPriceMap);
//				System.out.println("---------------------------------");
//			}
		
			
		}

//		if(finalBidder!=null) {
//
//			System.out.println();
//			System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님 ]");
//			System.out.println(" 축하드립니다!");
//			System.out.println();
//			
//			finalBidder.setCashHoldings(finalBidder.getCashHoldings()-quotePrice);
//			
//		}
		
			
		if(finalBidder!=null) {
		
			System.out.println();
			System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님 ]");
			System.out.printf(" %d원에 낙찰되었습니다. 축하드립니다!" , bidPrice);
			System.out.println();
			
			finalBidder.setCashHoldings(finalBidder.getCashHoldings()-bidPrice);
			
		}
		
		
		return finalBidder;
		
	}
	
	

	// 낙찰된 정보 상세 내역
	public void InformaionView(User user) {
		System.out.printf("\n\n\n★★★★★ %s님 입찰 정보 상세 내역 ★★★★★\n", user.getUserName());
		int randomInfo = 0;
		Information information = null;
		String[] increaseList = null;
		String[] decreaseList = null;

		if(infos.size()!=0) {
		randomInfo = random.nextInt(infos.size()); // 10 -> 0~9 범위
		information = infos.get(randomInfo);
		System.out.println(information.toString());
		infos.remove(randomInfo);
		}

		
		increaseList = information.getIncrease().clone(); // 상승 종목 리스트 가져오기
		decreaseList = information.getDecrease().clone(); // 하락 종목 리스트 가져오기
		
		incDecList = service.updateInfomationPrice(stocks, information, increaseList, decreaseList);
			
		
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
				System.out.print("매수 종목 번호 입력 ▶ ");
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
		System.out.println();
		System.out.printf("▷ %s %d주, 총 %d원에 매수 완료! ◁", buyStock.getStockName(), input, input*buyStock.getStockPrice());
		System.out.println();
	}

	
	
	// 매도 페이지
	public void sellView(User user) {

		int input=0;
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
		
		user.sellStock(sellStock, input, service.findStock(user, sellStock));
		
	}
	

	// 이벤트 발생
	public void evernView() {
	}

	// 순위
	public void rankingView() {

	}

}