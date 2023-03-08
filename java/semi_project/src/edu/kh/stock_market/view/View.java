package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.service.Service;

public class View {

	
	private Stock stock = new Stock();
	private Service service = new Service();
	Set<User> userSet = new HashSet<>();  // 회원 리스트
	Set<Stock> stockSet = new TreeSet<>(); // 주식 종목 리스트
	
	LocalDate now = LocalDate.now();  // 현재 날짜 구하기
	int year = now.getYear();
	int month = now.getMonthValue();
	int day = now.getDayOfYear();
	
	
	/** 시작 화면
	 * 
	 */
	public void mainView() {
		
		
		Scanner scan = new Scanner(System.in);
		String menuChoice;
		
		
		do{
			
			try {
				
				System.out.println("============ 일개미 주식 거래소에 오신 것을 환영합니다!! ============");
				System.out.println();
				System.out.println("1. 거래소 입장하기");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
				System.out.println();
				
				
				while(true) {
					
					System.out.print("실행하고자 하는 메뉴를 입력해주세요 ▶ ");
					menuChoice = scan.nextLine();
					
					if(menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3"))
						break;
					
					System.out.println("입력 양식이 올바르지 않습니다. 다시 입력해주세요.");
					System.out.println();
				}
				
				
					
				switch(menuChoice) {
				case "1" : registerUserInfo(); break;
				case "2" : ruleView(); break;
				case "3" : System.out.println("게임을 종료합니다."); return;
				}
			
			
			}catch(InputMismatchException e) {
			
				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
				
				menuChoice = null;
			}
			
			break;
						

		}while(!menuChoice.equals(null));
		
		
		informationAuction();   // 랜덤으로 나타나게 하기
		
		stockDisplay();
		
		
		
	}
	
	
	/** 규칙 설명
	 * 
	 */
	public void ruleView() {
		
		
		Scanner scan = new Scanner(System.in);
		char backInput;
		
		
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
			
			System.out.print("뒤로가기 [B] ▶ ");
			backInput = scan.nextLine().toUpperCase().charAt(0);
			
			if(backInput=='B')
				break;
			
			else {
				
				System.out.println("올바른 키를 입력해주세요.");
				System.out.println();
			}
		}

	}
	
	
	/** 사용자 등록
	 * 
	 */
	public void registerUserInfo() {
	
		Scanner sc = new Scanner(System.in);
		
		int userCount=0;  // 등록 인원
		int count=1;  // iterator 순회 countcheck
		String inputUserName;
		
		Iterator<User> userIterator;
		
		System.out.println();
		
		do {

			System.out.println("[사용자 등록하기]");
			System.out.print("등록할 인원 : ");
			
			try {
				
				userCount = sc.nextInt();
				sc.nextLine();
				
				if(userCount>1) break;
				
				System.out.println();
				System.out.println("최소 2명 이상 입력해주세요!");
				System.out.println();
				userCount = 0;
				
				
			}catch(InputMismatchException e) {
				
				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
				userCount=1;
				
			}
			
		}while(userCount==0);
		

		
		System.out.println();
		System.out.println("[닉네임을 입력해주세요]");
		
	
		
		for(int i=1; i<userCount+1; i++) {
			System.out.printf("USER %d ▶ ", i);
			inputUserName = sc.nextLine();
			
			if(inputUserName.equals("")) {
				System.out.println("닉네임을 제대로 입력해주세요.");
				i--;
				continue;
			}
			
			
			userSet = service.registerUserService(inputUserName);
			
			if(userSet==null) {
				System.out.println("중복된 닉네임입니다. 다시 입력해주세요.");
				i--;
			}
		}
	
		

		userIterator = userSet.iterator();  // 회원 리스트 순회를 위한 iterator
		
		
		while(userIterator.hasNext()) {
			User user = userIterator.next();
			
			if(count<userSet.size()) {
				System.out.print(user.getUserName() + "님, ");				
			}else {
				System.out.println(user.getUserName() + "님 등록이 완료되었습니다.");
			}
			
			count++;
			
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
		Set<User> userBidder = new HashSet<>(userSet);		
		
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
				
			userIterator = userBidder.iterator();  // 회원 리스트 순회를 위한 iterator
			
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
	
		if(!bidPriceMap.isEmpty()) {

			System.out.println();
			System.out.println("[ 최종 낙찰자 : " + finalBidder.getUserName() + "님   ]");
			System.out.println(" 축하드립니다!");
			System.out.println();
			
		}
		
	}
	
	
	/** 종목 전광판
	 * 
	 */
	public void stockDisplay() {
		
		
		Scanner scan = new Scanner(System.in);
		Iterator<User> userIterator;
		Set<User> stockDealUserSet = new HashSet<>(userSet);
		int stockNum;  // 종목 선택
		
		Set<Stock> stockRiseOfFallSet = service.stockRiseOrFall(stock.getStockList());
		
		
		ArrayList<Stock> stockList = new ArrayList<>(stockRiseOfFallSet);  // 번호 매칭하기 위한 ArrayList
		
		
		System.out.printf("[%d년 %d월 %d일]\n", year, month, day);
		System.out.println("---------------------------------------------------------------------------");		
		System.out.println(" 번호                종목                        가격                           상승/하락률               남은 주식수");
		System.out.println("---------------------------------------------------------------------------");
		
				
		
		for(int i=1; i<stockList.size(); i++) {
			System.out.printf("[%d]   %10s        %10d       %10d%%           %2d\n"
					, i, stockList.get(i).getStockName(), stockList.get(i).getStockPrice(), stockList.get(i).getStockChangeRate(), stockList.get(i).getStockLeft());
		}

		
		System.out.println();
		System.out.println("종목 번호 선택 [패스 시 0 입력]");
		
		userIterator = stockDealUserSet.iterator();
		
		while(userIterator.hasNext()) {
			User user = userIterator.next();
			System.out.print(user.getUserName() + " ▶  ");
			stockNum = scan.nextInt();
			scan.nextLine();
			
			if(stockNum==0) {
				userIterator.remove();
				continue;
			}
		
			userInfoAndSelectStockOption(user, stockList.get(stockNum));
			
		}
		
	}
	
	
	
	/** 사용자 정보 및 종목 매수
	 * @param user
	 * @param stockNum
	 */
	public void userInfoAndSelectStockOption(User user, Stock stock) {
	
		
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println();
		System.out.printf("--------------- [%s 님의 계좌 잔고] ---------------\n", user.getUserName());
		System.out.println("총 평가 금액  : " + user.getProperty() + "원");
		System.out.println("전일 대비 ");
		
		System.out.println();
		System.out.println("1. 매수");
		System.out.println("2. 매도");
		System.out.println("3. 뒤로가기");
	
		System.out.println();
		
		while(true) {
			
			try {

				System.out.print(" 메뉴 선택 ▶ ");
				choice = scan.nextInt();
				scan.nextLine();
				
				if(choice==1 || choice==2 || choice==3)
					break;
				
			}catch(InputMismatchException e) {
				
				System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
			
			}catch(Exception e) {
				
				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
			}
			
		}
		

		switch(choice) {
		case 1 : buyView(); break;
		case 2 : sellView(); break;
		case 3 : return;
		}
	}
	
	
	
	/** 매수
	 * 
	 */
	public void buyView() {
		
		
	}
	
	
	/** 매도
	 * 
	 */
	public void sellView() {
		
	}
	
	
	/** 이벤트 발생
	 * 
	 */
	public void eventView() {
		
	}
	
	
	/** 순위
	 * 
	 */
	public void rankingView() {
		
	}
}
