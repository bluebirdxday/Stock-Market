package edu.kh.stock_market.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.service.Service;

public class View {
	
	
	private Service service = new Service();
	private Scanner scan = new Scanner(System.in);
	List<User> userList = null;

	
	/** 시작 화면
	 * 
	 */
	public void mainView() {
		
		int menuChoice = 0;
		
		
		do{
			
			try {
				
				System.out.println("============ 일개미 주식 거래소에 오신 것을 환영합니다!! ============");
				System.out.println();
				System.out.println("1. 거래소 입장하기");
				System.out.println("2. 규칙 설명");
				System.out.println("3. 게임 종료");
				System.out.println();
				
				
				System.out.print("실행하고자 하는 메뉴를 입력해주세요 ▶ ");
				menuChoice = scan.nextInt();
				scan.nextLine();
				
					
				switch(menuChoice) {
				case 1 : registerUserInfo(); break;
				case 2 : ruleView(); break;
				case 3 : System.out.println("게임을 종료합니다."); return;
				default : System.out.println("입력 양식이 틀렸습니다! 다시 입력해주세요.");
				}
				
			
			}catch(InputMismatchException e) {
			
				System.out.println("[warning]" + e.getClass().getName() + " : " + e.getMessage());
				
				menuChoice = -1;
			}
			
			break;
						

		}while(menuChoice!=0);
		
		
		
		
	}
	
	
	/** 규칙 설명
	 * 
	 */
	public void ruleView() {
		
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
			
			System.out.println("뒤로가기 [B] ▶ ");
			backInput = scan.nextLine().toUpperCase().charAt(0);
			
			if(backInput=='B')
				break;
			
			else {
				
				System.out.println("올바른 키를 입력해주세요.");
				System.out.println();
			}
		}

	}
	
	
	public void registerUserInfo() {
		
		
		int userCount=0;
		
		System.out.println();
		System.out.println("[사용자 등록하기]");
		System.out.print("등록할 인원 : ");
		
		userCount = scan.nextInt();
		scan.nextLine();

		
		System.out.println();
		System.out.println("[닉네임을 입력해주세요]");
		
		
		for(int i=1; i<userCount+1; i++) {
			System.out.printf("USER %d : ", i);
			userList = service.registerUserService(scan.nextLine());
		}
		
		
		for(int i=0; i<userList.size()-1; i++) {
			System.out.print(userList.get(i).getUserName() + "님, ");
		}
		
		System.out.println(userList.get(userCount-1).getUserName() + "님 등록이 완료되었습니다.");

	}
	
	
	public void informationAuction() {
		
		
		System.out.println();
		
	}
	
	
	
	
	
}
