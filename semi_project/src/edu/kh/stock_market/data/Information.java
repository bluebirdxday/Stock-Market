package edu.kh.stock_market.data;

import java.util.ArrayList;

public class Information {

	private String eventName; // 정보 발생 문자열
	private String[] increase = null; // 상승 종목 배열
	private String[] decrease = null; // 하락 종목 배열

	public Information() {

	}

	public Information(String eventName, String[] increase, String[] decrease) {
		this.eventName = eventName;
		this.increase = increase;
		this.decrease = decrease;
	}

	@Override
	public String toString() {
		return eventName;
	}


	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String[] getIncrease() {
		return increase;
	}

	public void setIncrease(String[] increase) {
		this.increase = increase;
	}

	public String[] getDecrease() {
		return decrease;
	}

	public void setDecrease(String[] decrease) {
		this.decrease = decrease;
	}
	
	

}