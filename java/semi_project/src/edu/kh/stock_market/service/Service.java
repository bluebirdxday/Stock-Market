package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;

public class Service {

	private Set<User> userSet;
	
	public Service() {	
		userSet = new HashSet<>();
	}
	
	
	/** 사용자 등록
	 * @param userName 사용자 닉네임
	 * @return userList 사용자 리스트
	 */
	public Set<User> registerUserService(String userName) {
	
		Iterator<User> ir = userSet.iterator();
		
		while(ir.hasNext()) {
			User u = ir.next();
			
			if(u.getUserName().equals(userName))
				return null;
		}
				
		userSet.add(new User(userName));
		
		return userSet;
		
	}
	
	
	/** 정보 입찰 경매
	 * 입찰가 비교해서 최고 금액 낙찰
	 * @param map<사용자 객체, 입찰가>
	 * @return 최고 입찰가 사용자 이름 or 최고 입찰 금액이 여러명 존재할 경우 랜덤으로 사용자 하나 선택
	 */
	public User auctionService(Map<User, Integer> map) {
	
	
		Entry<User, Integer> maxEntry = null;
		ArrayList<User> arrayList = new ArrayList<>();
		int count = 0; // 중복 체크 값
		
		
		for(Map.Entry<User, Integer> entry : map.entrySet()) {
				
			if(maxEntry==null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}else if(entry.getValue() == maxEntry.getValue()) {
				arrayList.add(entry.getKey());
				count++;
			}

		}
		
		if(arrayList.isEmpty()) {
			return maxEntry.getKey();
		}else {
			return arrayList.get((int)Math.random()*count);			
		}
		
	}

	
//	public static String[] generateRandomSetKeysSwap(Set<User> set) {
//        
//		int size=set.size();
//		
//        String result[] = new String[size];
//        
//        Random random = new Random();
//        
//        int maxNumber = size;
//        Iterator<User> iterator = set.iterator();
//        
//        int resultPos=0;
//        while (iterator.hasNext()) {
//            result[resultPos++] = iterator.next().getUserName();
//        }
//       
//        for (int loop = 0; loop < size; loop++) {
//            int randomNumber1 = random.nextInt(maxNumber);
//            int randomNumber2 = random.nextInt(maxNumber);
//            
//            String temp=result[randomNumber2];
//            result[randomNumber2]=result[randomNumber1];
//            result[randomNumber1]=temp;
//        }
//		
//        return result;
//    }
//

	

	public Set<Stock> stockRiseOrFall(Set<Stock> stockList) {
		
		Random random = new Random();
		Iterator<Stock> iterator = stockList.iterator();
		
		while(iterator.hasNext()) {
			Stock stock = iterator.next();
			
			if(stock.getStockPrice()<100)
				stock.setStockChangeRate(random.nextInt(10));
			else
				stock.setStockChangeRate(random.nextInt(21)-10);
			
		}
		
		return stockList;
	}
	
	public boolean calcDay(int month, int day) {
		
		switch(month) {
		case 1 :
		case 3 :
		case 5 :
		case 7 :
		case 8 :
		case 10 :
		case 12 :
			if(day==31) 
				return false;
		case 2 :
			if(day==28)
				return false;
		case 4 :
		case 6 :
		case 9 :
		case 11 :
			if(day==30)
				return false;
		}
		
		return true;
	}
}
