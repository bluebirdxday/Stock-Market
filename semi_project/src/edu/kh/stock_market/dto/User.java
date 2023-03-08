package edu.kh.stock_market.dto;

import java.util.ArrayList;
import java.util.List;

public class User{
   private int cash = 100000;   //시작시 현금
   private String userName;     // 유저 이름
   private int cashHoldings;     // 보유 현금
   private List<Stock> stocks;   // 보유 종목
   private int property;         // 총 자산
   
   public User() {
   }
   
   public User(String userName) {
      this.userName = userName;
      cashHoldings = cash;
      stocks = new ArrayList<Stock>();
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



   public List<Stock> getStocks() {
      return stocks;
   }


   public void setStocks(List<Stock> stocks) {
      this.stocks = stocks;
   }


   public int getProperty() {
      return property;
   }


   public void setProperty(int property) {
      this.property = property;
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