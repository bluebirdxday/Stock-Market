package project.mini.dto;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private String type;
	private String name;
	private boolean longName;

	private int price;
	private int prevPrice;
	
	public Stock() {}
	public Stock(String type, String name, int price) {
		this.type = type;
		this.name = name;
		this.price = price;
		this.prevPrice = price;
		this.longName = false;
	}

	public Stock(String type, String name, int price, boolean longName) {
		this(type, name, price);
		this.longName = longName;
	}

	public boolean isLongName() {
		return longName;
	}
	public void setLongName(boolean longName) {
		this.longName = longName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrevPrice() {
		return prevPrice;
	}
	public void setPrevPrice(int prevPrice) {
		this.prevPrice = prevPrice;
	}
	
	// 주식정보 초기화
	public static List<Stock> initStocks() {
		List<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("바이오","한미약품", 1000));
		stocks.add(new Stock("바이오","삼성바이오로직스", 1500,true));
		stocks.add(new Stock("항공","대한항공", 1000));
		stocks.add(new Stock("여행","모두투어", 500));
		stocks.add(new Stock("자율주행","테슬라", 2500));
		stocks.add(new Stock("자율주행","MS Soft", 1000));
		stocks.add(new Stock("자동차","현대모비스", 1500));
		stocks.add(new Stock("자동차","기아", 1000));
		stocks.add(new Stock("엔터","SM", 500));
		stocks.add(new Stock("엔터","하이브", 1500));
		stocks.add(new Stock("반도체","DB하이텍", 500));
		stocks.add(new Stock("반도체","SK 하이닉스", 2000,true));
		stocks.add(new Stock("IT","애플", 2000));
		stocks.add(new Stock("IT","삼성전자", 2500));
		stocks.add(new Stock("금융","KB 금융", 1000));
		stocks.add(new Stock("금융","미래에셋대우 증권", 2500,true));
		stocks.add(new Stock("건설","현대건설", 500));
		stocks.add(new Stock("건설","LH", 1500));
		stocks.add(new Stock("보험","한화손해보험", 1000,true));
		stocks.add(new Stock("보험","DB손해보험", 2000));
		stocks.add(new Stock("화학","코스모화학", 2500));
		stocks.add(new Stock("화학","SK종합화학", 1500));
		stocks.add(new Stock("식품","CJ", 1000));
		stocks.add(new Stock("식품","오뚜기", 500));
		stocks.add(new Stock("뷰티","아모레퍼시픽", 500,true));
		stocks.add(new Stock("뷰티","LG 생활건강", 1000,true));
		return stocks;
	}
	
	public String getInfo() {
		double rate = ((double)price - prevPrice) / prevPrice * 100;
		// "\t종류\t\t종목명\t\t현재 금액\t\t등락률";
		// 종류 종목명 현재 금액 등락률 남은 주식 수 이름 총 평가금액 전일대비
		return String.format("\t%s\t%s%s\t%,7d\t        %6.1f%%\t",
					type,
					name,
					longName?"":"\t",
					price,
					rate);
	}
}