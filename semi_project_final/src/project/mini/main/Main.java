package project.mini.main;

import java.util.Scanner;

import project.mini.play.Play;
import project.mini.view.View;


public class Main {
	final private String GAME_START = "1";
	final private String SHOW_RULES = "2";
	final private String QUIT_PROGRAM = "3";
	
	private View view;
	private Scanner sc;
	private String input;
	
	public Main() {
		sc = new Scanner(System.in);
		view = new View();
		menu(); // 메뉴메서드 호출
	}
	
	private void menu() {
		while(true) { // 게임 종료시까지 반복
			view.drawMain();
			try {
			input = sc.nextLine();
			} catch(Exception e) {
				view.inputError();
			}

			// 게임 시작			
			switch(input) {
			case GAME_START:
				new Play(); // Play 객체 생성
				break; // 메인화면으로 돌아가기
				
			// 규칙 설명
			case SHOW_RULES:
				view.drawRule();
				System.out.print("돌아가려면 Enter 키를 입력하세요.");
				sc.nextLine(); // 엔터 입력받을 때까지 슬립.
				break; // 메인화면으로 돌아가기
			
			// 게임 종료
			case QUIT_PROGRAM:
				view.drawQuit();
				return;	// 메인 종료
				
			default:
				view.inputError();
			}
		}
	}
	
	
}