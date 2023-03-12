package project.mini.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
	// 상수
	final private int SEED_MONEY = 100000; // 초기 설정 현금값
	
	// 변수
	private String name; // 플레이어명
	private int cash; // 보유 현금
	private List<PlayerStock> stocks; // 보유 주식
	private int prevAssets;
	private int totalAssets;
	private int totalStocks;
	
	// 기본 생성자
	public Player() {
		this.cash = SEED_MONEY; // 보유현금에 시드머니 대입.
		this.prevAssets = SEED_MONEY;
		this.stocks = new ArrayList<>(); // 보유주식리스트 ArrayList 생성
	}
	
	// 매개변수 생성자
	public Player(String name) {
		this(); // 기본 생성자 호출
		this.name = name; // 이름에 받아온 이름 대입
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public List<PlayerStock> getStocks() {
		return stocks;
	}
	public void setPlayerStocks(List stocks) {
		this.stocks = stocks;
	}
	
	public String getInfo(List<Stock> stocks) {
		totalAssets = cash;
		totalStocks = 0;
		for(PlayerStock ps:this.stocks) {
			for(Stock s : stocks) {
				if(ps.name.equals(s.getName())) {
					totalStocks += s.getPrice()*ps.count;
					break;
				}
			}
		}
		totalAssets += totalStocks;
		double rate = ((double)totalAssets - prevAssets) / prevAssets * 100;
		return String.format("\t%s\t%,16d  %,16d     %,16d\t\t\t%6.1f%%",
				name,
				cash,totalStocks,totalAssets,
				rate);
	}
	
	// 주식 구매
	public void buyStock(Stock buyStock, int count) {
		// 구매할 주식이 이미 보유중인지 비교
		for (PlayerStock s : stocks) {
			// 구매할 주식의 이름과 보유중식 주식의 이름 중 같은 것이 있는 지 비교
			// 같은 것이 있다면, 처리해주고 메서드 종료.
			if (s.getName().equals(buyStock.getName())) {
				// 평단가 재설정. (평단가*보유수량+현재가격*구매수량)/(보유수량+구매수량)
				s.setBuyPrice((s.getBuyPrice() * s.getCount() +
						buyStock.getPrice() * count) /
						(s.getCount() + count));
				// 보유주식 수량에 수량 추가
				s.setCount(s.getCount() + count);
				// 현재 가격 * 구매 수량만큼 보유현금 차감
				cash -= buyStock.getPrice() * count;
				return;
			}
		}
		// 같은 것이 없었다면 새로 추가. 유저주식 생성(구매주식이름, 구매주식수량, 구매주식의 현재가격을 평단가에 대입)
		stocks.add(new PlayerStock(buyStock.getName(), buyStock.isLongName(), count, buyStock.getPrice()));
		// 현재 가격 * 구매 수량만큼 보유현금 차감
		cash -= buyStock.getPrice() * count;
	}
}