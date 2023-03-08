package edu.kh.stock_market.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.stock_market.service.Service;
import edu.kh.stock_market.dto.Stock;



public class Service extends Stock {
    private static final int MIN_CHANGE_PERCENT = -10;
    private static final int MAX_CHANGE_PERCENT = 10;
    private int prevPrice;
	private ArrayList<Stock> stockList;
    
    public Service(String name, int price) {
        super(name, price);
        this.prevPrice = price;
    }
    
    public void updatePrice() {
        int changePercent = (int) (Math.random() * (MAX_CHANGE_PERCENT - MIN_CHANGE_PERCENT + 1)) + MIN_CHANGE_PERCENT;
        int changeAmount = (int) (getStockPrice() * changePercent / 100.0);
        int newPrice = getStockPrice() + changeAmount;
        this.prevPrice = getStockPrice();
        setStockPrice(newPrice);
    }
    
    public double getChangeRate() {
        return (getStockPrice() - prevPrice) / (double) prevPrice * 100.0;
    }
    
    public void setStockList(ArrayList<Stock> stockList) {
		this.stockList = stockList;
	}

	public static void main(String[] args) {
        List<Service> stockList = new ArrayList<>();
        stockList.add(new Service("삼성전자", 1000));
        stockList.add(new Service("SK하이닉스", 2000));
        stockList.add(new Service("NAVER", 3000));
        stockList.add(new Service("현대차", 2500));
        stockList.add(new Service("삼성바이오로직스", 8000));
        stockList.add(new Service("LG화학", 15000));
        stockList.add(new Service("삼성SDI", 4000));
        stockList.add(new Service("셀트리온", 1500));
        stockList.add(new Service("카카오", 18000));
        stockList.add(new Service("기아차", 1000));
        stockList.add(new Service("POSCO", 2000));
        stockList.add(new Service("삼성물산", 3000));
        stockList.add(new Service("LG전자", 4000));
        stockList.add(new Service("SK이노베이션", 5000));
        stockList.add(new Service("삼성생명", 50000));
        stockList.add(new Service("현대모비스", 2500));
        stockList.add(new Service("LG생활건강", 3000));
        stockList.add(new Service("KB금융", 7000));
        stockList.add(new Service("한국전력", 6000));
        stockList.add(new Service("SK텔레콤", 4000));
        
        for (int i = 0; i < 10; i++) {
            System.out.println("==== Day " + (i+1) + " ====");
            for (Service stock : stockList) {
                stock.updatePrice();
                System.out.printf("%s: %,d원 (%+d%%)\n", stock.getStockName(), stock.getStockPrice(), (int)stock.getChangeRate());
            }
            System.out.println();
        }
    }
	
	
    
}