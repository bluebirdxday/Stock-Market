package edu.kh.stock_market.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.service.Service;

public class View {
	
	private Scanner sc = new Scanner(System.in);
	private Service service = new Service();
	
	private List<User> userList = new ArrayList<User>();
	
	/**
	 * 메인화면
	 * */
	public void mainView() {
		int input = 0;
		
		do {
			try {
				System.out.println("일개미 주식 거래소에 오신 것을 환영합니다!!*\n");
				System.out.println("1. 거래소 입장하기");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
				System.out.println();
				
				System.out.print("메뉴를 선택하세요 >> ");
				
				int selectMenu = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(selectMenu) {
				case 1: ruleView(); break;
				case 2: registerUserInfo(); break;
				case 3: System.out.println("게임을 종료합니다."); break;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
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
	 * 규칙 설명
	 * */
	private void ruleView() {
		System.out.println();
		System.out.println("================================ 기본 규칙 ================================\n");
		System.out.println("1. 총 20턴을 돌며 최종적으로 가장 많은 자산을 보유한 사람이 승리합니다.");
		System.out.println("2. 1주당 1만원 주식을 거래할 수 있습니다.");
		System.out.println("3. 초기 자본은 10만원입니다.");
		System.out.println("4. 주가는 상황에 따라 수시로 변동됩니다.");
		System.out.println("5. 턴 마다 매수, 매도, 패스를 선택 할 수 있습니다. 매수, 매도는 각 1번씩만 가능합니다 (총 2회)");
		System.out.println("6. 모든 종목은 10주로 한정되어 있습니다.");
		System.out.println("7. 정보 입찰이 격일로 진행됩니다.");
		System.out.println("8. 가장 큰 금액을 배팅한 사람이 정보를 얻을 수 있습니다.");		
		System.out.println("9. 이벤트가 발생하는 날이 있으며 이에 따라 주가가 변동됩니다.");
		System.out.println("10. 이벤트 발생 시 해당 턴에서 첫번째로 입장한 사람만 이벤트 내용을 확인 할 수 있으며, "
				+ "5주 이내로만 매수할 수 있습니다.");
		System.out.println("===========================================================================");
		
		System.out.println();
		
		while(true) {
			System.out.println("뒤로가기 >> ");
		}
	}
	

	/**
	 * 사용자 등록
	 * */
	private void registerUserInfo() {
		int userCount = 0;
		int count = 1;
		
		System.out.println("\n---[사용자 등록하기]---\n");
		System.out.print("등록할 인원 수 : ");
		
		Scanner scan;
		userCount = scan.nextInt();
		scan.nextLine();
		
		System.out.println("닉네임을 입력해주세요. ");

		for(int i=1; i<userCount+1; i++) {
			System.out.printf("USER %d : ", i);
			userSet = service.registerUserService(scan.nextLine());
		}
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			
			if(count<userSet.size()) {
				System.out.print(user.getUserName() + "님, ");				
			}else {
				System.out.println(user.getUserName() + "님 등록이 완료되었습니다.");
			}
			
			count++;
		}
	}

	
	
	/**
	 * 정보 경매
	 * */
	private void informationAuction() {
		
		int bidPriceNow = 1000;
		
		LocalDate now = LocalDate.now();
		System.out.println(now);
		System.out.println("[정보 경매를 시작합니다.]");
		System.out.println();
		System.out.println("현재 입찰가 : 1000원");
		System.out.println();
		
		/* ...?
		USER 1 : P

		USER 3 : 12000

		…
		
		USER N : 10000
		 */
		
		List<User> list = new ArrayList<>();
		
		for(User u : userList) {
			
		}
		
		for(User u : userList) {
			if(u.getBidPrice() > 50000) {
				System.out.println("제시 가능한 최대 금액을 초과합니다. 다시 입력해주세요.");
			}else if(u.getBidPrice() < bidPriceNow) {
				System.out.println("현재 입찰가 보다 낮습니다. 다시 입력해주세요.");
			}else {
				System.out.println("낙찰가 : " + bidPriceNow);
				System.out.printf("USER%d님이 정보를 낙찰받으셨습니다.", u);  // ???..........
			}
		}
		System.out.println();
	}
	
	
	/**
	 * 낙찰된 정보의 상세 내용
	 * */
	private void informationView () {
		
//		private ArrayList<String> infoDescription;	// 정보 설명
		
		System.out.println("★★★★★★★★★★★★★★★★★ 정보 ★★★★★★★★★★★★★★★★★");
		System.out.println("(정보 랜덤 출력)");
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
	}
	
	
	/**
	 * 종목 전광판
	 * */
	private void stockDisplay() {
		
		LocalDate now = LocalDate.now();
		System.out.println("[" + now + "]");
		// 주식장 (종목 - 가격 - 상승/하락률 - 남은 주식 수)
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	
	/**
	 * 사용자 정보 및 종목 매수/매도/패스 선택
	 * */
	private void userInfoAndSelectStockOption() {
		
	}
	
	
	/**
	 * 매수 페이지
	 * */
	private void buyView () {
		
	}
	
	
	/**
	 * 매도 페이지
	 * */
	private void sellView (){
		
	}
	
	
	/**
	 * 이벤트 발생
	 * */
	private void eventView() {
		
	}
	
	
	/**
	 * 순위
	 * */
	private void rankingView() {
		
	}
	
	
}

