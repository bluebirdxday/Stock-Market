package edu.kh.stock_market.data;

import java.util.List;

import edu.kh.stock_market.dto.Stock;

public class Event {

	private String eventDescription;	// 이벤트 설명
	private List<Stock> risingStocks; // 상승 종목
	private List<Stock> decliningStocks;  // 하락 종목
	private double rateOfRise;  // 상승률
	private double rateOfDecline;  // 하락률
	
}