package edu.kh.stock_market.data;

import java.util.List;

import edu.kh.stock_market.dto.Stock;

public class Event {

	private String eventDescription;	// 이벤트 설명
	private List<Stock> stocks; // 종목
	private double rate;  // 변동률
	
	
	public Event() {
		
	}
	
	
	
	public Event(String eventDescription, List<Stock> stocks, double rate) {

		this.eventDescription = eventDescription;
		this.stocks = stocks;
		this.rate = rate;
	}



	public String getEventDescription() {
		return eventDescription;
	}
	
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public List<Stock> getStocks() {
		return stocks;
	}
	
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
	
}
