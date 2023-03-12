package project.mini.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.mini.dto.Event;
import project.mini.dto.Player;
import project.mini.dto.Stock;
import project.mini.view.View;

public class Play {
	public static final int MAX_OF_TURN = 20;
	private static final int MIN_CHANGE_PERCENT = -10;
	private static final int MAX_CHANGE_PERCENT = 10;
	
	private final int MIN_NUMBER_OF_PLAYER = 2;
	private final int MAX_NUMBER_OF_PLAYER = 5;
	private final int MAX_NUMBER_OF_PLAYER_NAME = 5;
	
	private Scanner sc;
	private View view;
	private List<Player> players;
	private List<Stock> stocks;
	private List<Event> events;
	private int turnPlayerIndex;
	private String input;
	private int turn;
	
	public Play() {
		sc = new Scanner(System.in);
		view = new View();
		players = new ArrayList<>();
		stocks = Stock.initStocks();
		events = Event.initEvents();
		turn = 1;
		input="";

		registPlayer(); // 플레이어 등록
		play();
	}
	
	// 플레이어 등록
	private void registPlayer() {
		int playerNum;
		view.setDisp("플레이어를 등록합니다.",View.LINE_OF_FIRST);
		view.setSelect("등록할 플레이어 수를 입력하세요. (2~5)",View.LINE_OF_FIRST);
		while(true) {
			try {
				view.writeDisplay();
				input = sc.nextLine();
				playerNum = Integer.parseInt(input);
				if(playerNum < MIN_NUMBER_OF_PLAYER || playerNum > MAX_NUMBER_OF_PLAYER) throw new Exception();
				break;
			} catch(Exception e) {view.inputError();}
		}
		
		for (int i = 0; i < playerNum; i++) {
			view.setSelect(i+1+"번째 플레이어의 이름을 입력하세요.",View.LINE_OF_FIRST);
			view.setSelect("- 공백문자는 입력할 수 없습니다.");
			view.setSelect("- 이름은 최대 "+MAX_NUMBER_OF_PLAYER_NAME+"글자까지 입력 가능합니다.");
			String player;
			while (true) {
				try {
					view.writeDisplay();
					player = sc.nextLine();
					if(player.length()==0 || player.length()>5) throw new Exception();
					break;
				}catch (Exception e) {
					view.inputError();
				}
			}
			players.add(new Player(player));
			view.setDisp(players.get(i).getName() + "님 등록되었습니다.");
		}
		view.setDisp(new String[]{"플레이어 등록이 완료되었습니다.","게임을 시작합니다."});
		view.cleanSelect();
		view.setSelect("Enter 키를 누르면 게임이 시작됩니다.",0);
		view.writeDisplay();
		sc.nextLine();
	}
	
	// 게임 시작
	private void play() {
		for (turn = 1; turn <= MAX_OF_TURN; turn++) {
			for (turnPlayerIndex = 0; turnPlayerIndex < players.size(); turnPlayerIndex++) {
				boolean pass=false;
				try {
					view.clean();
					view.drawAllInfo(stocks, players, turn, turnPlayerIndex);
					view.drawGameMenu();
					view.writeDisplay();
					input = sc.nextLine();
					switch (input) {
					case "1":buy();break;
					case "2":sell();break;
					case "3":pass=pass();break;
					case "0":
						if(quit()) return;
					}
				} catch (Exception e) {
					view.inputError();
				}
				if(!pass)
					turnPlayerIndex--;
			}
			view.clean();
			if(turn==20) {
				return;
			}
			view.setDisp(turn+"번째 턴이 종료되었습니다.");
			view.setDisp("주식 가격이 변동됩니다.");
			updatePrice(stocks);
			view.setSelect("Enter키를 누르면 다음 턴이 시작됩니다.");
			view.writeDisplay();
			sc.nextLine();
		}
	}
	public void updatePrice(List<Stock> stocks) {
		for (Stock s : stocks) {
			int changePercent = (int) (Math.random() * (MAX_CHANGE_PERCENT - MIN_CHANGE_PERCENT + 1))
					+ MIN_CHANGE_PERCENT;
			int changeAmount = (int) (s.getPrice() * changePercent / 100.0);
			int newPrice = s.getPrice() + changeAmount;
			s.setPrevPrice(s.getPrice());
			s.setPrice(newPrice);
		}
	}
	
	private void buy() {
		Stock buyStock;
		int value;

		while(true) {
			try {
				view.clean();
				view.drawAllInfo(stocks, players, turn, turnPlayerIndex);
				view.setSelect("구입할 종목을 선택하세요.");
				int sp = 0;
				for(int i=0;i<stocks.size();i++) {
					String content = String.format("%d. %s          ",i+1,
							stocks.get(i).getName());
					if(i%6==0)
						sp++;
					view.appendSelect(content,sp);
				}
				view.writeDisplay();
				input = sc.nextLine();
				// 구매할 종목 정보 대입
				buyStock = stocks.get(Integer.parseInt(input)-1);
				break;
			} catch (Exception e) {
				view.inputError();
			}
		}

		
		/* 예외상황.
		 * 1. 숫자 외 입력
		 * 2. input*buyStock.getStockPrice()가 user.getCashHoldings()를 초과하는 경우   
		 * */
		while (true) {
			view.cleanSelect();
			view.setSelect(buyStock.getName() + "의 현재 가격은 " + buyStock.getPrice() + "원 입니다.");
			view.setSelect("몇 주 구매하시겠습니까? ");
			try {
				view.writeDisplay();
				input = sc.nextLine();
				value = Integer.parseInt(input);
				if (Integer.parseInt(input)*buyStock.getPrice() > players.get(turnPlayerIndex).getCash()) {
					view.inputError("보유 현금이 부족합니다.");
					continue;
				}
				view.cleanSelect();
				view.setSelect(String.format("%s %d주의 가격은 %,d원 입니다.", buyStock.getName(),value,buyStock.getPrice()*value));
				view.setSelect("정말 매수하시겠습니까?");
				view.setSelect("1. 매수");
				view.setSelect("2. 취소");
				view.writeDisplay();
				input = sc.nextLine();
				if(input.equals("1")) break;
				else if(input.equals("2")) continue;
				else throw new Exception();
			} catch (Exception e) {
				view.inputError();
			}
		}
		players.get(turnPlayerIndex).buyStock(buyStock,value);
		view.cleanSelect();
		view.setSelect(String.format("%s %d주를 %d원에 매수하였습니다.", buyStock.getName(), value, value*buyStock.getPrice()));
		view.setSelect("돌아가려면 Enter 키를 누르세요.");
		view.writeDisplay();
		sc.nextLine();
	}
	
	private void sell() {
		
	}
	
	private boolean pass() {
		boolean pass;
		while(true) {
			try {
				view.cleanSelect();
				view.setSelect("정말 패스하시겠습니까?");
				view.setSelect("1. 패스");
				view.setSelect("2. 취소");
				view.writeDisplay();
				input = sc.nextLine();
				if(input.equals("1")) {pass=true;break;}
				else if(input.equals("2")) {pass=false;break;}
				else throw new Exception();
			} catch(Exception e) {
				view.inputError();
			}
		}
		return pass;
	}
	
	private boolean quit() {
		boolean quit;
		while(true) {
			try {
				view.cleanSelect();
				view.setSelect("메인으로 돌아가면 게임이 종료되며, 현재 상황이 저장되지 않습니다.");
				view.setSelect("정말 메인으로 돌아가시겠습니까?");
				view.setSelect("1. 돌아간다.");
				view.setSelect("2. 취소");
				view.writeDisplay();
				input = sc.nextLine();
				if(input.equals("1")) {quit=true;break;}
				else if(input.equals("2")) {quit=false;break;}
				else throw new Exception();
			} catch(Exception e) {
				view.inputError();
			}
		}
		return quit;
	}
}