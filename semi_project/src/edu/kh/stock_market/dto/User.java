package edu.kh.stock_market.dto;

import java.util.List;

public class User {
	
	final int START_CASH = 100_000;				//시작시 현금량
	final String SCREEN = "KH Stock Market";
	final int STOCK_QUANTITIY = 10; 			//초기 주식 수 
	
	private int day;			    //날짜
	private int turn;			    //턴
	private String userName;	//유저이름
	private int userNum;		  //유저순서
	private int cashHoldings;	//보유 현금
    private List<Stock> stocks; 
	private int property;		  // 총 자산
	private int bidPrice;		  // 입찰가

}
