package edu.kh.stock_market.dto;

import java.util.HashMap;
import java.util.Map;

public class User{
   private int cash = 100000;   //시작시 현금
   private String userName;     // 유저 이름
   private int cashHoldings;     // 보유 현금
   private Map<Stock, Integer> stocks;   // 보유 종목
   private int property;         // 총 자산
   
   public User() {
   }
   
   public User(String userName) {
      this.userName = userName;
      cashHoldings = cash;
      stocks = new HashMap<Stock, Integer>();
      property = cash;
   }

   
   public String getUserName() {
      return userName;
   }


   public void setUserName(String userName) {
      this.userName = userName;
   }


   public int getCashHoldings() {
      return cashHoldings;
   }


   public void setCashHoldings(int cashHoldings) {
      this.cashHoldings = cashHoldings;
   }


   
   public Map<Stock, Integer> getStocks() {
	   return stocks;
	}

   public void setStocks(Map<Stock, Integer> stocks) {
	   this.stocks = stocks;
	   
   }
   
  
   public int holdStockCount(Stock stock) {     // key값(종목명)으로 value값(주식 수) 찾기
	   return stocks.get(stock.getStockName());
   }
   
   
   public int getProperty() {
      return property;
   }
   
   
   // 보유 종목 Map에 종목  추가
   public void addStock(Stock stock, int stockNum) {
	   stocks.put(stock, stockNum);   
   }
  
   // 보유하고 있는 종목 Map의  주식 수 변경
   public void updateStock(Stock stock, int stockNum) {
	   
	   stocks.put(stock, stocks.get(stock) + stockNum);
   }
   
   // 총 자산 업데이트 (매수, 매도 시)
   public void updateProperty(int totalPrice) {
	   cashHoldings -= totalPrice;     // 보유현금 = 보유 현금 - 매수 종목 가격 * 매수 주식 수
	   property = cashHoldings + totalPrice;   // 총자산 = 보유현금 + 매수 종목 가격 * 매수 주식 수
   }
   
   

   
   @Override
   public boolean equals(Object obj) {
      if(obj instanceof User) {
         User temp = (User)obj;
         return userName.equals(temp.userName);
      }
      return false;
   }


   @Override
   public int hashCode() {
      return userName.hashCode();
   }

   @Override
   public String toString() {
      return "User [cash=" + cash + ", userName=" + userName + ", cashHoldings=" + cashHoldings + ", stocks=" + stocks
            + ", property=" + property +"]";
   }

   
  
}