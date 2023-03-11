package edu.kh.stock_market.data;

import java.util.ArrayList;

public class Information {

	private ArrayList<String> infoDescription;	// 정보 설명
	// private ArrayList<String> infoDescription; // 일단 안써서 주석 처리 했어요! 삭제해도 되면 합시다!

	private String eventName; // 정보 발생 문자열
	private String[] increase; // 상승 종목 배열
	private String[] decrease; // 하락 종목 배열

	public Information() {

	}

	public Information(String eventName, String[] increase, String[] decrease) {
		this.eventName = eventName;
		this.increase = increase;
		this.decrease = decrease;
	}

	@Override
	public String toString() {
		return eventName + "이/가 발생하였습니다.";
	}

}