package project.mini.view;

import java.util.List;
import java.util.Scanner;

import project.mini.dto.Player;
import project.mini.dto.PlayerStock;
import project.mini.dto.Stock;
import project.mini.play.Play;

public class View {
	public final static int LINE_OF_FIRST = 0;
	
	private final String TITLE = "[KH STOCK MARKET]───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
	private final String SELECT = "[선택지]──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
	private final String INPUT = "[입력]────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
	private final String INFO_DIVISION = String.format("\t%s\t%s\t\t%6s      %s\t\t"
			+ "%s\t\t%6s\t\t%s\t%s\t%s",
			"종류","종목명","현재 금액","전턴대비 등락률"
			,"플레이어명","보유 현금","    보유주식평가금액","    총 자산(현금+주식)","    전턴대비 등락률");
	private final String PLAYER_STOCKS_DIVISION = String.format("\t%s\t\t%s\t\t%s\t%s\t\t%s\t%s",
			"종목명","보유량","평균매입단가","매수 금액","평가 금액","     매수대비 등락률");
	private final String LINE = "\t------------------------------------------------";
	private final String LONG_LINE = "\t---------------------------------------------------------------------------------------";
	
	private final int DISP_SIZE = 35;
	private final int SELECT_SIZE = 9;
	
	private String[] disp;
	private String[] select;
	private int dp;
	private int sp;
	private Scanner sc;
	
	// 초기화(View 사용할 준비)
	public View() {
		// 화면그리기에 사용할 String 배열 생성
		sc = new Scanner(System.in);
		disp = new String[DISP_SIZE];
		select = new String[SELECT_SIZE];
		dp = 0;
		sp = 0;
		// 전체 지우기 메서드 호출 (생성한 배열에 공백문자 대입) 
		clean();
	}
	
	// 현재 저장된 값 그리기
	public void writeDisplay() {
		System.out.println(TITLE);
		for (String s : this.disp)
			System.out.println(s);
		System.out.println(SELECT);
		for (String s : this.select)
			System.out.println(s);
		System.out.println(INPUT);
		System.out.print(" ▶ ");
	}
	
	// 전체 지우기
	public void clean() {
		cleanDisp();
		cleanSelect();
	}
	// 디스플레이 지우기
	public void cleanDisp() {
		dp=0;
		for(int i=0;i<disp.length;i++) disp[i] = "";
	}
	// 선택지 지우기
	public void cleanSelect() {
		sp=0;
		for(int i=0;i<select.length;i++) select[i] = "";
	}
	
	
	// 메인화면 그린 후 출력
	public void drawMain() {
		// 메인화면 그리기
		dp = 9;
		disp[dp++] = "\t\t\t\t _   __ _   _   _____  _                 _     ___  ___              _           _   ";
		disp[dp++] = "\t\t\t\t| | / /| | | | /  ___|| |               | |    |  \\/  |             | |         | |  ";
		disp[dp++] = "\t\t\t\t| |/ / | |_| | \\ `--. | |_   ___    ___ | | __ | .  . |  __ _  _ __ | | __  ___ | |_ ";
		disp[dp++] = "\t\t\t\t|    \\ |  _  |  `--. \\| __| / _ \\  / __|| |/ / | |\\/| | / _` || '__|| |/ / / _ \\| __|";
		disp[dp++] = "\t\t\t\t| |\\  \\| | | | /\\__/ /| |_ | (_) || (__ |   <  | |  | || (_| || |   |   < |  __/| |_ ";
		disp[dp++] = "\t\t\t\t\\_| \\_/\\_| |_/ \\____/  \\__| \\___/  \\___||_|\\_\\ \\_|  |_/ \\__,_||_|   |_|\\_\\ \\___| \\__|";
		
		sp = 0;
		select[sp++] = "1. 게임 시작";
		select[sp++] = "2. 규칙 설명";
		select[sp++] = "3. 게임 종료";
		// 출력메서드 호출
		writeDisplay();
	}
	
	// Disp Setter
	public void setDisp(String disp) {
		try {this.disp[dp++] = disp;} catch(Exception e) {}
	}
	
	public void setDisp(String disp, int dp) {
		this.dp=dp;
		setDisp(disp);
	}
	
	public void setDisp(String[] disp) {
		try {
			for (int i = 0; i < disp.length; i++) 
				this.disp[dp++] = disp[i];
		} catch(Exception e) {}
	}
	
	public void setDisp(String[] disp, int dp) {
		this.dp=dp;
		setDisp(disp);
	}
	
	// Select Setter
	public void setSelect(String select) {
		try {this.select[sp++] = select;} catch(Exception e) {}
	}
	public void setSelect(String select, int sp) {
		this.sp = sp;
		setSelect(select);
	}
	public void setSelect(String[] select) {
		try {
			for (int i = 0; i < select.length; i++)
				this.select[sp++] = select[i];
		} catch (Exception e) {}
	}
	public void setSelect(String[] select, int sp) {
		this.sp = sp;
		setSelect(select);
	}
	public void appendSelect(String select) {
		try {this.select[sp] += select;} catch(Exception e) {}
	}
	public void appendSelect(String select, int sp) {
		this.sp = sp;
		appendSelect(select);
	}
	
	public void drawAllInfo(List<Stock> stocks, List<Player> players, int turn, int turnIndex) {
		dp = 1;
		disp[dp++] = "\t"+turn+"/"+Play.MAX_OF_TURN+" - "+players.get(turnIndex).getName()+"님의 차례입니다.";
		disp[dp++] = "\t주식 정보\t\t\t\t\t\t\t플레이어 정보";
		disp[dp++] = LINE+LONG_LINE;
		// 종류 종목명 현재 금액 등락률 남은 주식 수 이름 총 평가금액 전일대비
		disp[dp++] = INFO_DIVISION;
		disp[dp++] = LINE+LONG_LINE;
		for (Stock s : stocks) disp[dp++] = s.getInfo();
		disp[dp] = LINE;
		dp = 6;
		for (Player p : players) disp[dp++] += p.getInfo(stocks);
		disp[dp++] += LONG_LINE;
		dp++;
		disp[dp++] += "\t현재 턴 플레이어 "+players.get(turnIndex).getName()+"님의 보유 주식 정보";
		disp[dp++] += LONG_LINE;
		disp[dp++] += PLAYER_STOCKS_DIVISION;
		disp[dp++] += LONG_LINE;
//		for (PlayerStock ps : players.get(turnIndex).getStocks()) disp[dp++] += ps.getInfo();
		if(players.get(turnIndex).getStocks().size()==0) {
			dp+=3;
			disp[dp] += "\t\t\t\t\t현재 보유중인 주식이 없습니다.";
			dp+=4;
			disp[dp] += LONG_LINE; 
		} else {
			for (PlayerStock ps : players.get(turnIndex).getStocks()) {
				disp[dp++]+=ps.getInfo(stocks);
			}
		disp[dp++] += LONG_LINE;
		}
	}
	
	
	
	
	
	
	
	
	
	
	// 규칙설명 화면 그린 후 출력(미구현)
	public void drawRule() {
		// 전체 지우기
		clean();
		/* 규칙 내용 작성하기
		 * 
		 * 
		 * 
		 * */
		
		// 출력메서드 호출
		writeDisplay();
	}
	
	// 종료화면 그린 후 출력
	public void drawQuit() {
		// 전체 지우기
		clean();
		disp[15]="\t\t\t\t\t\t\t\t게임을 종료합니다.";
		writeDisplay();
	}
	
	// 입력 에러 처리
	public void inputError() {
		writeDisplay();
		System.out.print("잘못 입력하셨습니다. Enter키를 입력하여 돌아가세요.");
		sc.nextLine();
	}
	public void inputError(String msg) {
		writeDisplay();
		System.out.print(msg);
		System.out.println(" Enter키를 입력하여 돌아가세요.");
		sc.nextLine();
	}

	public void drawGameMenu() {
		cleanSelect();
		select[sp++] = "1. 매수";
		select[sp++] = "2. 매도";
		select[sp++] = "3. 패스";
		select[sp++] = "0. 메인으로 돌아가기";
	}
	
	public void drawBuyMenu() {
		cleanSelect();
	}
}