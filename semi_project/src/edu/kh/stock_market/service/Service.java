package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;

public class Service {
	
//	private Stock[] stockArr = new Stock[10]; // 
	

	private static final int MIN_CHANGE_PERCENT = -10;
    private static final int MAX_CHANGE_PERCENT = 10;
    private int prevPrice;
    
    //List<Stock> stockList = new ArrayList<>();
	
	private Scanner sc = new Scanner(System.in);
	
	Stock stock = new Stock();
	List<User> users = new ArrayList<User>();

//	public String[] registerUserService(String name) { /*내가*/
//		
//		String[] user = new String[name];// user 명단만(닉네임만) 저장하는 배열
//		
//		System.out.println();
//		
//		System.out.println("[닉네임을 입력해주세요.]");
//		for(int i=0; i<name; i++) {
//			try {
//				System.out.printf("USER %d : ", i+1);
//				user[i] = sc.next();
//				
//			} catch (ArrayIndexOutOfBoundsException e) {
//				System.out.println("이미 입력한 인원 수 만큼 닉네임이 설정되었습니다.");
//			} 
//		} 
//		return user;
//	}
	
	
	public List<User> registerUserService(String name) {
		
		User user = new User(name);
		users.add(user);
		return users;
	}
	
	
	// 반환형?
	public double displayService() { // 종목-상승,하락률
		return 0;
		
	}
	
	
		
    public List<Stock> updatePrice() {
    	
    	List<Stock> stockList = stock.getStockList();
    	
    	for(int i=0; i<stockList.size(); i++) {
    		// 1) 변동률
    		int changePercent = (int) (Math.random() * (MAX_CHANGE_PERCENT - MIN_CHANGE_PERCENT + 1)) + MIN_CHANGE_PERCENT;
    		// 2) 변동값
    		int changeAmount = (int) (stockList.get(i).getStockPrice() * changePercent / 100.0);
    		// 가격 : 원래 값 + 2)변동값  = 변동 반영된 값
    		int newPrice = stockList.get(i).getStockPrice() + changeAmount;
            stockList.get(i).setStockPrice(newPrice);
            stockList.get(i).setStockIncDec(changePercent);
            System.out.println(stockList.get(i));
    	}
    	
            return stockList;
            
    	
       
        
        
    	 
    	
    	
    	
    }	
		
		
		
		



	public String[] userRandomTurn() { //반환값?
		
		return null;
		
	}
	
	
	
	public String[] buyService() {
		return null;
		
	}
	
	public String[] sellService() {
		return null;
	}





}
