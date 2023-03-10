package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.dto.UserStock;
import edu.kh.stock_market.data.Information;

public class Service {
	
	private static final int MIN_CHANGE_PERCENT = -10;
	private static final int MAX_CHANGE_PERCENT = 10;
	List<User> users = new ArrayList<>();

	public Service() {
	}

	// 주식정보 초기화
	public List<Stock> initStocks() {
		List<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("한미약품", 1000));
		stocks.add(new Stock("삼성바이오로직스", 1500));
		stocks.add(new Stock("대한항공", 1000));
		stocks.add(new Stock("모두투어", 500));
		stocks.add(new Stock("테슬라", 2500));
		stocks.add(new Stock("MS soft", 1000));
		stocks.add(new Stock("현대모비스", 1500));
		stocks.add(new Stock("기아", 1000));
		stocks.add(new Stock("SM", 500));
		stocks.add(new Stock("하이브", 1500));
		stocks.add(new Stock("DB하이텍", 500));
		stocks.add(new Stock("SK 하이닉스", 2000));
		stocks.add(new Stock("애플", 2000));
		stocks.add(new Stock("삼성전자", 2500));
		stocks.add(new Stock("KB 금융", 1000));
		stocks.add(new Stock("미래에셋대우 증권", 500));
		stocks.add(new Stock("현대건설", 500));
		stocks.add(new Stock("LH", 1500));
		stocks.add(new Stock("한화손해보험", 1000));
		stocks.add(new Stock("DB손해보험", 2000));
		stocks.add(new Stock("코스모화학", 2500));
		stocks.add(new Stock("SK종합화학", 1500));
		stocks.add(new Stock("CJ", 1000));
		stocks.add(new Stock("오뚜기", 500));
		stocks.add(new Stock("아모레퍼시픽", 500));
		stocks.add(new Stock("LG 생활건강", 1000));
		return stocks;
	}
	
	// 정보 초기화
		public List<Information> initInfos() {
			List<Information> infos = new ArrayList<>(); 
			infos.add(new Information("코로나 바이러스 발생",
					new String[]{"한미약품","CJ","애플"},
					new String[] {"대한항공","아모레퍼시픽", "KB 금융","SM"}));
			infos.add(new Information("코로나 관련 규제 완화",
					new String[]{"대한항공","아모레퍼시픽", "KB 금융","SM"},
					new String[] {"한미약품","CJ","애플"}));
			infos.add(new Information("코로나 백신 개발",
					new String[]{"삼성바이오로직스","하이브","모두투어"},
					new String[] {"삼성전자"}));
			infos.add(new Information("조류 독감 발생",
					new String[]{"종목없음"},
					new String[] {"오뚜기"}));
			infos.add(new Information("하이브리드 세금 혜택",
					new String[]{"기아"},
					new String[] {"종목없음"}));
			infos.add(new Information("하이브리드 차량 화재",
					new String[]{"DB손해보험"},
					new String[] {"현대모비스","SK 하이닉스"}));
			infos.add(new Information("자율주행 4단계 성공",
					new String[]{"테슬라"},
					new String[] {"종목없음"}));
			infos.add(new Information("자율주행 주행 중 사고",
					new String[]{"종목없음"},
					new String[] {"테슬라"}));
			infos.add(new Information("러시아, 우크라이나 전쟁 발발",
					new String[]{"CJ","코스모화학"},
					new String[] {"삼성바이오로직스", "대한항공", "테슬라", "기아", "SM", "DB하이텍", "삼성전자", "미래에셋대우 증권", "LH", "DB손해보험", "코스모화학", "오뚜기", "LG 생활건강"}));
			infos.add(new Information("신도시 개발",
					new String[]{"현대건설"},
					new String[] {"종목없음"}));
			infos.add(new Information("chat GPT 출시",
					new String[]{"DB하이텍","애플"},
					new String[] {"종목없음"}));
			infos.add(new Information("메타버스 SNS 유행",
					new String[]{"애플","하이브"},
					new String[] {"종목없음"}));
			infos.add(new Information("물가상승, 금리인상",
					new String[]{"종목없음"},
					new String[] {"한미약품", "모두투어", "테슬라", "기아", "SM", "DB하이텍", "애플", "미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강"}));
			infos.add(new Information("금리 인하",
					new String[] {"한미약품", "모두투어", "테슬라", "기아", "SM", "DB하이텍", "애플", "미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강"},
					new String[] {"종목없음"}));
			infos.add(new Information("아이돌 열애설",
					new String[]{"종목없음"},
					new String[] {"하이브"}));
			infos.add(new Information("냉동식품 속 이물질",
					new String[]{"종목없음"},
					new String[] {"CJ"}));
			infos.add(new Information("대한항공 아시아나 합병",
					new String[]{"대한항공"},
					new String[] {"종목없음"}));
			infos.add(new Information("SM 매각",
					new String[]{"SM"},
					new String[] {"종목없음"}));
			infos.add(new Information("중고폰 거래 키오스크 시장 진출",
					new String[]{"애플","SK 하이닉스"},
					new String[] {"종목없음"}));	
			return infos;
		}


	

	// 주식가격 업데이트
	public void updatePrice(List<Stock> stocks) {
		for (Stock s : stocks) {
			int changePercent = (int) (Math.random() * (MAX_CHANGE_PERCENT - MIN_CHANGE_PERCENT + 1))
					+ MIN_CHANGE_PERCENT;
			int changeAmount = (int) (s.getStockPrice() * changePercent / 100.0);
			int newPrice = s.getStockPrice() + changeAmount;
			s.setPrevPrice(s.getStockPrice());
			s.setStockPrice(newPrice);
		}
	}
	
	// 사용자 등록
	public List<User> registerUserService(String name) {
		User user = new User(name);
		users.add(user);
		return users;
	}
	
	
	
	/** 날짜 계산
	 * @param month
	 * @param day
	 * @return
	 */
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

	public UserStock findStock(User user, Stock stock) {
		
		List<UserStock> stockList = user.getUserStockList();
		UserStock foundStock = null;
		
		for(int i=0; i<stockList.size(); i++) {
			
			if(stockList.get(i).getStockName().equals(stock.getStockName())) {
				
				foundStock = stockList.get(i);
				break;
			}
		}
		
		return foundStock;
		
	}

	
	
}