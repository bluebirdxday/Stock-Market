package edu.kh.stock_market.service;

import java.util.ArrayList;

import edu.kh.stock_market.dto.Stock;


public class Service {
	
private ArrayList<Stock> stockList;
	
	public Service() {
		stockList = new ArrayList<>();
		
		stockList.add(new Stock("삼성전자", 81000));
		stockList.add(new Stock("SK하이닉스", 130000));
		stockList.add(new Stock("LG화학", 830000));
		stockList.add(new Stock("NAVER", 400000));
		stockList.add(new Stock("현대차", 239500));
		stockList.add(new Stock("POSCO", 387500));
		stockList.add(new Stock("셀트리온", 308000));
		stockList.add(new Stock("KB금융", 54800));
		stockList.add(new Stock("삼성물산", 128000));
		stockList.add(new Stock("LG전자", 128000));
		stockList.add(new Stock("카카오", 116500));
		stockList.add(new Stock("신한지주", 38750));
	}
	
	// getter
	public ArrayList<Stock> getStockList() {
		return stockList;
	}
}