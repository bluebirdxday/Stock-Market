package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.stock_market.data.Information;
import edu.kh.stock_market.dto.Stock;
import edu.kh.stock_market.dto.User;
import edu.kh.stock_market.dto.UserStock;

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

	// 경매 정보 초기화
	public List<Information> initInfos() {
		List<Information> infos = new ArrayList<>();
		infos.add(new Information("코로나 바이러스가 점차 확산되기 시작합니다.", new String[] { "한미약품", "CJ", "애플" },
				new String[] { "대한항공", "아모레퍼시픽", "KB 금융", "SM" }));
		infos.add(new Information("코로나 바이러스 관련 규제가 완화될 예정입니다.", new String[] { "대한항공", "아모레퍼시픽", "KB 금융", "SM" },
				new String[] { "한미약품", "CJ", "애플" }));
		infos.add(new Information("화이자가 코로나 백신 중 첫 FDA 승인을 받았습니다.", new String[] { "삼성바이오로직스", "하이브", "모두투어" },
				new String[] { "삼성전자" }));
		infos.add(new Information("한 오리농장에서 조류 인플루엔자 의심 축이 발생되었습니다.", new String[] { "종목없음" }, new String[] { "오뚜기" }));
		infos.add(new Information("하이브리드 차량 구매자에 대한 취득세 면제 조치를 시행할 예정입니다.", new String[] { "기아" },
				new String[] { "종목없음" }));
		infos.add(new Information("하이브리드 차량 화재가 발생하였습니다.", new String[] { "DB손해보험" },
				new String[] { "현대모비스", "SK 하이닉스" }));
		infos.add(new Information("자율주행 4단계 성공 소식이 들려옵니다.", new String[] { "테슬라" }, new String[] { "종목없음" }));
		infos.add(new Information("자율주행 주행 중 사고가 발생했다는 소식이 있습니다.", new String[] { "종목없음" }, new String[] { "테슬라" }));
		infos.add(new Information("러시아와 우크라이나 전쟁이 발발 될 기미가 보입니다.", new String[] { "CJ", "코스모화학" },
				new String[] { "삼성바이오로직스", "대한항공", "테슬라", "기아", "SM", "DB하이텍", "삼성전자", "미래에셋대우 증권", "LH", "DB손해보험",
						"오뚜기", "LG 생활건강" }));
		infos.add(new Information("신도시 개발 사업을 추진할 예정입니다.", new String[] { "현대건설" }, new String[] { "종목없음" }));
		infos.add(new Information("Open API가 개발한 인공지능 챗봇 chat GPT가 출시되었습니다.", new String[] { "DB하이텍", "애플" },
				new String[] { "종목없음" }));
		infos.add(
				new Information("SNS 상에서 메타버스가 유행할 조짐이 보입니다.", new String[] { "애플", "하이브" }, new String[] { "종목없음" }));
		infos.add(new Information("물가가 상승하고, 금리가 인상되었습니다.", new String[] { "종목없음" }, new String[] { "한미약품", "모두투어",
				"테슬라", "기아", "SM", "DB하이텍", "애플", "미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강" }));
		infos.add(new Information("금리가 인하될 예정입니다.", new String[] { "한미약품", "모두투어", "테슬라", "기아", "SM", "DB하이텍", "애플",
				"미래에셋대우 증권", "LH", "DB손해보험", "SK종합화학", "오뚜기", "LG 생활건강" }, new String[] { "종목없음" }));
		infos.add(new Information("모 아이돌의 열애설이 발표될 예정입니다.", new String[] { "종목없음" }, new String[] { "하이브" }));
		infos.add(new Information("냉동식품 속에서 이물질을 발견했다는 신고가 여럿 들어오고 있습니다.", new String[] { "종목없음" },
				new String[] { "CJ" }));
		infos.add(new Information("대한항공과 아시아나 합병의 조짐?!", new String[] { "대한항공" }, new String[] { "종목없음" }));
		infos.add(new Information("모 회사에서 SM을 인수할 것이라는 소식이 들려옵니다.", new String[] { "SM" }, new String[] { "종목없음" }));
		infos.add(new Information("중고폰 거래도 키오스크로 간편하게?", new String[] { "애플", "SK 하이닉스" }, new String[] { "종목없음" }));
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

	public List<Map<Stock, Integer>> updateInfomationPrice(List<Stock> stocks, Information information,
			String[] increaseList, String[] decreaseList) {

		int changeAmount;
		List<Map<Stock, Integer>> incDecList = new ArrayList<>();
		Map<Stock, Integer> newIncPrice = new HashMap<>();
		Map<Stock, Integer> newDecPrice = new HashMap<>();

		if (information.getIncrease() != null) { // 상승 종목이 존재한다면

			for (int i = 0; i < increaseList.length; i++) {
				for (int j = 0; j < stocks.size(); j++) {

					if (stocks.get(j).getStockName().equals(increaseList[i])) {
						int increaseRate = (int) (Math.random() * (40 - 30 + 1)) + 30; // 30~40 랜덤 생성
						changeAmount = (int) (stocks.get(j).getStockPrice() * increaseRate / 100.0);
						newIncPrice.put(stocks.get(j), stocks.get(j).getStockPrice() + changeAmount);
						// HahsMap에 추가 key:Stock , value:newPrice

					}

				}
			}

		}

		if (information.getDecrease() != null) { // 상승 종목이 존재한다면

			for (int i = 0; i < decreaseList.length; i++) {
				for (int j = 0; j < stocks.size(); j++) {

					if (stocks.get(j).getStockName().equals(decreaseList[i])) {

						int decreaseRate = (int) (Math.random() * (-30 - (-40) + 1)) - 40; // 30~40 랜덤 생성
						changeAmount = (int) (stocks.get(i).getStockPrice() * decreaseRate / 100.0);
						newDecPrice.put(stocks.get(j), stocks.get(j).getStockPrice() + changeAmount);
						// HahsMap에 추가 key:Stock , value:newPrice
					}

				}
			}

		}

		incDecList.add(newIncPrice);
		incDecList.add(newDecPrice);

		return incDecList;

	}

	public void applyIncDecRateToStock(List<Stock> stocks, List<Map<Stock, Integer>> incDecList) {

		
		Map<Stock, Integer> newIncPrice = incDecList.get(0);
		Map<Stock, Integer> newDecPrice = incDecList.get(1);

		for (Stock stock : stocks) {
			if (newIncPrice.containsKey(stock)) {
				stock.setStockPrice(newIncPrice.get(stock));
			}

			if (newDecPrice.containsKey(stock)) {
				stock.setStockPrice(newDecPrice.get(stock));
			}
		}

	}

	// 사용자 등록
	public List<User> registerUserService(String name) {
		User user = new User(name);
		users.add(user);
		return users;
	}

	/**
	 * 날짜 계산
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public boolean calcDay(int month, int day) {

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day == 31)
				return false;
		case 2:
			if (day == 28)
				return false;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day == 30)
				return false;
		}

		return true;
	}

	public UserStock findStock(User user, Stock stock) {

		List<UserStock> stockList = user.getUserStockList();
		UserStock foundStock = null;

		for (int i = 0; i < stockList.size(); i++) {

			if (stockList.get(i).getStockName().equals(stock.getStockName())) {

				foundStock = stockList.get(i);
				break;
			}
		}

		return foundStock;

	}

	public Stock findUserStock(User user, UserStock userStock, List<Stock> stocks) {

		Stock foundStock = null;

		for (int i = 0; i < stocks.size(); i++) {

			if (stocks.get(i).getStockName().equals(userStock.getStockName())) {

				foundStock = stocks.get(i);
				break;
			}
		}

		return foundStock;

	}

}