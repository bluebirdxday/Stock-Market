package edu.kh.stock_market.data;

import java.util.List;

import edu.kh.stock_market.dto.Stock;

public class Event {

	private String eventDescription;	// 이벤트 설명
	private List<Stock> stocks; // 종목
	private double rate;  // 변동률
}
