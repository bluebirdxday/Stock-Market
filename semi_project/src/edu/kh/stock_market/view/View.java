package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
	private List<Map<Stock, Integer>> incDecList = new ArrayList<Map<Stock, Integer>>(); // 정보 경매 상승,하락 종목들

	LocalDate now = LocalDate.now(); // 현재 날짜 구하기
	int year = now.getYear();
	int month = now.getMonthValue();
	int day = now.getDayOfMonth();

	int amOrPm = 1;

	int nextTime = 0; // 정보 경매 등락률 다음 오전/오후 타임에 적용

	final int turn = 10;

	Random random = new Random();

	public View() {
		sc = new Scanner(System.in);
		service = new Service();
		mainView();
	}

	// 메인화면
	public void mainView() {

		int input;
		boolean done = false;

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
					done = enterStockExchange();
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

			if (done == true)
				break;
		}
	}

	// 거래소 입장
	public boolean enterStockExchange() {

		registerUserInfo();
		stocks = service.initStocks();
		infos = service.initInfos(); // 서비스에서 정보 생성 초기화
		userInfoAndSelectStockOption();
		rankingView(users);

		return true;
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
		System.out.println("[2] 초기 자본은 10만원입니다.");
		System.out.println("[3] 주가는 상황에 따라 수시로 변동됩니다.");
		System.out.println("[4] 턴 마다 매수, 매도, 패스를 선택 할 수 있습니다.");
		System.out.println("[5] 정보 입찰이 무작위로 진행됩니다.");
		System.out.println("[6] 낙찰된 정보에 따라 주가가 변동됩니다. ");
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

		int dDay = turn - 1; // 최종 장마감 며칠 남았는지 환산

		for (int j = 1; j <= turn; j++) {

			amOrPm = j % 2;

			if ((int) (Math.random() * 2) == 0)
				informationAuction();

			if (j != 1)
				service.updatePrice(stocks);

			if (day == nextTime && !incDecList.isEmpty()) {
				service.applyIncDecRateToStock(stocks, incDecList);
			}

			for (int i = 0; i < users.size(); i++) {

				System.out.printf("\n\n %d년 %d월 %d일   %s\t", year, month, day, (amOrPm == 1 ? "오전" : "오후"));

				if (dDay != 0)
					System.out.printf("    D-%d\n", dDay);
				else
					System.out.println("D-Day");

				disStocks();
				user = users.get(i);
				userStocks = user.getUserStockList();

				int totalPrice = 0; // 사용자 모든 보유 주식 총 평가 가격

				for (Stock stock : stocks) {

					// 주식 리스트를 돌면서 사용자 보유 주식 리스트와 일치하는 종목이 있는지 확인
					UserStock userStock = service.findStock(user, stock); // 일치하는 종목 찾음
					if (userStock != null) {
						totalPrice += userStock.getStockCount() * stock.getStockPrice();
					} // 주식 평가 가격 = 주식 평가 가격 + 사용자 보유 주식 개수 * 주식 종목 현재 가격
				}

				user.updateProperty(totalPrice);

				System.out.println();
				System.out.println("-----------------------------------");
				System.out.println("[ " + user.getUserName() + "님의 자산 ]");
				System.out.println("총 자산  : " + user.getProperty() + "원");
				System.out.println("현   금 : " + user.getCashHoldings() + "원");

				Iterator<UserStock> ir = userStocks.iterator();
				int currentTotalStockPrcie = 0;
				int purchaseTotalAmount = 0;
				int totalGainsAndlosses = 0;
				int totalAveragePrice = 0; // 평단의 합

				while (ir.hasNext()) {
					UserStock userStock = ir.next();

					currentTotalStockPrcie += service.findUserStock(user, userStock, stocks).getStockPrice()
							* userStock.getStockCount(); // 현재주가
					purchaseTotalAmount += userStock.getAveragePrice() * userStock.getStockCount(); // 매입금액
					totalGainsAndlosses += currentTotalStockPrcie - purchaseTotalAmount; // 평가손익
					totalAveragePrice += userStock.getAveragePrice() * userStock.getStockCount(); // 평단의 합
				}

				System.out.print("평가손익 : ");
				if (totalGainsAndlosses > 0)
					System.out.print("+ ");
				System.out.println(totalGainsAndlosses + "원");

				double totalReturn; // 총 수익률

				System.out.print("총 수익률 : ");

				if (totalAveragePrice == 0)
					totalReturn = 0;
				else {
					totalReturn = (double) totalGainsAndlosses / totalAveragePrice * 100;
				}

				if (totalReturn > 0.0)
					System.out.print("+ ");

				System.out.printf("%.2f%%\n", totalReturn);

				if (userStocks.size() != 0) {

					System.out.println();
					System.out.println(
							"==============================================================================================================");
					System.out.println("종목명\t\t평단\t\t보유수량\t\t매입금액\t\t현재주가\t\t평가손익\t\t수익률");
					System.out.println(
							"==============================================================================================================");

					Iterator<UserStock> iterator = userStocks.iterator();
					int currentStockPrcie = 0; // 현재주가
					int purchaseAmount = 0; // 매입금액
					int gainsAndlosses = 0; // 평가손익

					while (iterator.hasNext()) {
						UserStock userStock = iterator.next();

						String stockName = userStock.getStockName();

						// 현재의주가 *보유수를 분모로 두고 현재주가 * 보유수 - 당시주가* 보유수를 빼고 이를 100으로 곱하면 수익이 나오죠
						//

						currentStockPrcie = service.findUserStock(user, userStock, stocks).getStockPrice()
								* userStock.getStockCount(); // 현재주가
						purchaseAmount = userStock.getAveragePrice() * userStock.getStockCount(); // 매입금액
						gainsAndlosses = currentStockPrcie - purchaseAmount; // 평가손익

						if (!userStock.getStockName().equals("삼성바이오로직스"))
							stockName += "\t";
						System.out.printf("%s\t%d\t\t%d\t\t%d\t\t%d\t\t", stockName, userStock.getAveragePrice(), // 종목명,
																													// 평단
								userStock.getStockCount(), purchaseAmount, currentStockPrcie); // 보유수량, 매입금액, 현재 주가

						if (gainsAndlosses > 0)
							System.out.print("+");
						System.out.print(gainsAndlosses + "\t\t");
						// 평가 손익 = 현재주가 - 매입금액

						if ((double) purchaseAmount / gainsAndlosses > 0.0)
							System.out.print("+");
						System.out.printf("%.2f%%\n", (double) gainsAndlosses / purchaseAmount * 100); // 수익률 =
																										// 매입금액(=투자원금)/손익*100

					}

					System.out.println(
							"==============================================================================================================");

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
						if (buyView(user))
							continue;
						else
							break;
					case 2:
						if (!userStocks.isEmpty()) {
							sellView(user);
							break;
						}
						
						else {
							System.out.println("현재 보유하고 있는 주식이 없습니다! 다른 메뉴를 선택해주세요");
							System.out.println("-----------------------------------");
							continue;
						}
					case 3:
						System.out.println("[ 아직 매수 / 매도가 가능합니다. 패스하시겠습니까? ]");
						System.out.println("1. 패스        2. 돌아가기");
						System.out.println();
						System.out.print("실행할 메뉴를 선택하세요 ▶ ");

						int input2 = sc.nextInt();

						if (input2 == 1)
							break;

						if (input2 == 2) {
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

			} //for문 

			nextTime = day + 1;
			dDay -= 1;

			if (amOrPm != 1) {

				if (service.calcDay(month, day)) {
					day++;
				} else {
					month++;
					day = 1;
				}

			}

		}
	}

//	/** 정보 경매
//	 * 
//	 */
//	public User informationAuction() {
//		
//		final int MAX_BIDPRICE = 50000;  // 최대 금액
//		int bidPrice = 1000; // 입찰가
//		int quotePrice=0;  // 회원 제시가
//		User finalBidder = null;   // 최종 낙찰자
//				
//		
////		Map<User, Integer> bidPriceMap = new HashMap<>(); // 입찰가 Map(key : userName, value : bidPrice)
//		
//		System.out.println();
//		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
//		System.out.printf("%d-%d-%d\n", year, month, day);
//		System.out.println("[정보 경매를 시작합니다]");
//		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
//		
//		System.out.println();
//		
//		System.out.println("원하는 금액을 제시해주세요. (최대 금액 : 50,000원) ");
//		System.out.println("패스하시려면 (0)를 입력해주세요.");
//		
//		System.out.println();
//		System.out.println("현재 입찰가 : " + bidPrice + "원");
//		System.out.println();
//		
//		
//		for(int i=0; i<2; i++) {
//				
//			for(int k=0; k<users.size(); k++) {
//				
//				try {
//
//					while(true) {
//
//						System.out.print(users.get(k).getUserName() + " ▶ ");
//						quotePrice = sc.nextInt();
//						sc.nextLine();
//						
//						if(quotePrice==0) {
//							break;
//						}
//						
//				     	else if(quotePrice<bidPrice) System.out.println("[X] 더 높은 금액을 제시해주세요 [X]"); // 회원 제시가 < 현재 입찰가
//						
//						else if(quotePrice>MAX_BIDPRICE) System.out.println("[X] 입찰 가능한 금액을 초과합니다 [X]");  // 회원 제시가 > 최대 금액
//
//						else {
//							bidPrice = quotePrice;
//							//bidPriceMap.put(users.get(k), quotePrice);
//							finalBidder = users.get(k);
//							break;
//						}
//						
//					}
//							
//				
//				}catch(InputMismatchException e) {
//					
//					System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
//					System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
//				
//				}catch(Exception e) {
//					
//					System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
//				}
//				
//			}
//			
//			if(bidPrice==1000) {
//				System.out.println("");
//				System.out.println("낙찰자가 존재하지 않습니다!");
//				System.out.println();
//				break;
//			}
//			
//			
//			System.out.println("---------------------------------");
//			
//		
//			
//		}
//
//			
//		if(finalBidder!=null) {
//		
//			System.out.println();
//			System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님 ]");
//			System.out.printf(" %d원에 낙찰되었습니다. 축하드립니다!" , bidPrice);
//			System.out.println();
//			
//			finalBidder.setCashHoldings(finalBidder.getCashHoldings()-bidPrice);
//			
//		}
//		
//		
//		return finalBidder;
//		
//	}

	public void informationAuction() {
		final int MAX_BIDPRICE = 50000; // 최대 금액
		int bidPrice = 1000; // 입찰가
		int quotePrice = 0; // 회원 제시가
		User finalBidder = null; // 최종 낙찰자
		String largestBidder = null; // 최대 입찰자의 이름 저장
		boolean[] pass = new boolean[users.size()]; // 패스한 유저 구분을 위한 플래그
		for (int i = 0; i < pass.length; i++)
			pass[i] = false; // pass에 기본값(false) 대입
		int passCounter = 0; // 패스한 유저 수를 카운트
		

		System.out.println();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.printf("\t\t%d-%d-%d\n", year, month, day);
		System.out.println("\t\t[정보 경매를 시작합니다]");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("  - 경매를 진행하여 낙찰받은 최종 1인은 투자에 유용한 정보를 얻게 됩니다.");
		System.out.println("  - 입찰가는 1000원부터 시작합니다.");
		System.out.println("  - 0을 입력 시 경매를 포기하게 됩니다.");
		System.out.println("  - 최대 금액은 " + MAX_BIDPRICE + "원이며, 그 이상 입력하실 경우 최대 금액으로 낙찰됩니다.\n");

		while(true) {
			System.out.println();
			// 낙찰자가 정해졌다면, 반복문 종료
			if (finalBidder != null)
				break;
			// 모두 패스했다면, 반복문 종료
			if (passCounter == users.size())
				break;
			for (int i = 0; i < users.size(); i++) {
				System.out.println();
				String name = users.get(i).getUserName();
				// 낙찰자가 정해졌다면, 반복문 종료
				if (finalBidder != null)
					break;
				// 모두 패스했다면, 반복문 종료
				if (passCounter == users.size())
					break;
				// 최대 입찰자를 제외하고 모두 패스했다면, 최대 입찰자를 낙찰자로 간주하고 반복문 종료
				if (passCounter == users.size() - 1 && largestBidder != null) {
					for (int j = 0; j < users.size(); j++)
						if (largestBidder.equals(users.get(j).getUserName())) {
							finalBidder = users.get(j);
							System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님 ]");
							break;
						}
					break;
				}

				// 패스한 유저는 입찰을 진행하지 않게 됨.
				if (pass[i])
					System.out.println(name + "님은 포기하셨으므로 다음 차례를 진행합니다.");

				// 패스한 유저가 아닌 경우.
				else {
					System.out.println("-----" + name + "님이 입찰을 진행합니다.-----\n");
					System.out.println(largestBidder == null ? "" : "현재 최대 입찰자는 " + largestBidder + "님 입니다.");
					System.out.println("현재 입찰가는 " + bidPrice + "원 입니다.");
					System.out.println("현재 입찰가보다 높은 금액을 입찰해주세요.");
					System.out.print("포기하시려면 0을 입력하세요.\n▶ ");

					// 예외시 다시 입력받을 수 있도록 입력받기 전에 무한 반복문
					while (true) {
						try {
							// 입찰가 입력받음.
							quotePrice = sc.nextInt();
							sc.nextLine();

							System.out.println();

							// 0을 입력한 경우(패스처리)
							if (quotePrice == 0) {
								// 현재 인덱스 패스변수 true로 설정하여 패스한 유저 구분
								pass[i] = true;
								// 패스한 유저 수 카운트 증가
								passCounter++;
								System.out.println(name + "님이 경매를 포기하셨습니다.");
								// 입력받는 반복문 종료
								break;
							}

							// 가지고 있는 현금보다 입찰 금액이 높은 경우
							else if (bidPrice > users.get(i).getCashHoldings())
								System.out.println("입찰 금액은 보유 현금을 초과할 수 없습니다.");

							// 입찰가보다 낮거나 같은 금액을 입력한 경우
							else if (quotePrice <= bidPrice)
								System.out.println("현재 입찰가보다 낮거나 같은 금액을 입력하셨습니다.");

							// 최대 입찰금액 이상 입찰한 경우 낙찰자로 설정하고 반복문 종료.
							else if (quotePrice >= MAX_BIDPRICE) {
								System.out.println("\n\n\n\n\n" + "최대 금액이상 입찰로 " + name + "님이 낙찰받으셨습니다.");
								// 현재 입찰자를 낙찰자로 설정
								finalBidder = users.get(i);
								// 반복문 종료
								break;
							}

							// 그 외의 경우(현재 입찰가보다 높은 입찰가를 입력하여 입찰가 갱신하는 경우)
							else {
								System.out.println(name + "님이 입찰가를 갱신하셨습니다.\n\n");
								// 입찰가를 현재 입찰가로 설정
								bidPrice = quotePrice;
								// 현재 입찰자의 이름 설정
								largestBidder = name;
								// 반복문 종료
								break;
							}
							// 재입력을 받아야 하는 경우
							System.out.print("다시 입력해주세요.\n▶ ");
						} catch (Exception e) {
							// 숫자외에 입력 한 경우
							System.out.println("잘못 입력하셨습니다.");
							System.out.print("다시 입력해주세요.\n▶ ");
						}
					}
				}
			}
		}

		// 낙찰자가 있는 경우
		if (finalBidder != null) {

			// 현재 기술 스택으로 구현할 수 없는 부분으로 유저들의 양심에 맡겨 처리
			System.out.printf(" %d원에 낙찰되었습니다. 축하드립니다!\n" , bidPrice);
			System.out.println("잠시 후 정보가 공개됩니다.");
			System.out.println(finalBidder.getUserName() + "님을 제외한 유저들은 눈을 감으세요!");
			System.out.println(finalBidder.getUserName() + "님 혼자만 보셔야해요!!!!!");
			System.out.println();
			System.out.println("Enter키를 입력하면 정보가 공개됩니다.");
			sc.nextLine();
			System.out.println();
			System.out.println();

			finalBidder.setCashHoldings(finalBidder.getCashHoldings()-bidPrice);
			InformaionView(finalBidder);

			System.out.println("\n\n");
			System.out.printf("현재 날짜는 %d년 %d월 %d일 %s이며,\n", year, month, day, (amOrPm == 1 ? "오전" : "오후"));
			System.out.println("해당 정보는 이 다음 날에 반영됩니다.");
			System.out.println("Enter 키를 누르면 정보가 사라집니다.");
			sc.nextLine();
			System.out.println("\n\n\n\n\n");
		}

		// 모두 패스하여 낙찰자가 없는 경우
		else {
			System.out.println("모든 유저가 입찰을 포기하였습니다.");
		}

	}
	
	

	// 낙찰된 정보 상세 내역
	public void InformaionView(User user) {
		System.out.printf("\n\n\n★★★★★ %s님 입찰 정보 상세 내역 ★★★★★\n", user.getUserName());
		int randomInfo = 0;
		Information information = null;
		String[] increaseList = null;
		String[] decreaseList = null;

		if (infos.size() != 0) {
			randomInfo = random.nextInt(infos.size()); // 10 -> 0~9 범위
			information = infos.get(randomInfo);
			System.out.println(information.toString());
			infos.remove(randomInfo);
		}

		increaseList = information.getIncrease().clone(); // 상승 종목 리스트 가져오기
		decreaseList = information.getDecrease().clone(); // 하락 종목 리스트 가져오기

		incDecList = service.updateInfomationPrice(stocks, information, increaseList, decreaseList);

	}

	public boolean buyView(User user) {

		int input = 0;
		Stock buyStock;

		/*
		 * 예외상황. 1. 숫자 외 입력 2. 매수 종목 번호 범위 초과
		 */
		while (true) {
			try {
				System.out.print("매수 종목 번호 입력 ▶ ");
				input = sc.nextInt();
				// 구매할 종목 정보 대입
				buyStock = stocks.get(input - 1);
				break;
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
		}

		/*
		 * 예외상황. 1. 숫자 외 입력 2. input*buyStock.getStockPrice()가 user.getCashHoldings()를
		 * 초과하는 경우
		 */
		while (true) {
			try {
				System.out.println();
				System.out.println(buyStock.getStockName() + "의 현재 가격은 " + buyStock.getStockPrice() + "원 입니다.");
				System.out.print("몇 주 구매하시겠습니까? ");
				input = sc.nextInt();
				sc.nextLine();
				if (input * buyStock.getStockPrice() > user.getCashHoldings()) {
					System.out.println();
					System.out.println("보유 현금이 부족합니다.");
					System.out.println();
					return true;
				}

				break;

			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}
		}

		user.buyStock(buyStock, input);
		System.out.println();
		System.out.printf("▷ %s %d주, 총 %d원에 매수 완료! ◁", buyStock.getStockName(), input,
				input * buyStock.getStockPrice());
		System.out.println();
		return false;
	}

	// 매도 페이지
	public void sellView(User user) {

		int input = 0;
		Stock sellStock;

		/*
		 * 예외상황. 1. 숫자 외 입력 2. 매도 종목 번호 범위 초과
		 */

		while (true) {

			try {
				System.out.print("매도 종목 번호 입력 : ");
				input = sc.nextInt();

				// 매도 종목 번호 입력 시 갖고 있는 종목이 아니라면 매도할 수 없도록
				if (service.findStock(user, stocks.get(input - 1)) != null) {

					// 매도할 종목 정보 대입
					sellStock = stocks.get(input - 1);
					break;

				} else {
					System.out.println();
					System.out.println("현재 보유하고 있는 종목이 아니므로 매도 불가합니다");
					System.out.println("-----------------------------------");
					return;
				}

			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				sc.nextLine();
			}

		}

		System.out.println();

		/*
		 * 예외상황. 1. 숫자 외 입력 2. input이 user의 userStock 리스트 내 stock 개수를 초과하는 경우
		 */
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

		System.out.println();
		System.out.printf("▷ %s %d, 총 %d원에 매도 완료! ◁\n", sellStock.getStockName(), input,
				input * sellStock.getStockPrice());

		user.sellStock(sellStock, input, service.findStock(user, sellStock));

	}

	// 최종 순위
	public void rankingView(List<User> userList) {

		Collections.sort(userList);

		System.out.println();
		System.out.println("★★★★★★★★★★★★★ 최종 순위 ★★★★★★★★★★★★★");

		System.out.println();
		System.out.printf("\tWINNER : %s님 축하드립니다!!\n", userList.get(0).getUserName());
		System.out.println();

		for (int i = 0; i < userList.size(); i++) {
			System.out.printf("\t[%d위] %s\t\t%d원\n", i + 1, userList.get(i).getUserName(),
					userList.get(i).getProperty());
		}

		System.out.println();
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");

	}

}