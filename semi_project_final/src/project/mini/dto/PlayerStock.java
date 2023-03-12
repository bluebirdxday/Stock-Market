package project.mini.dto;

import java.util.List;

public class PlayerStock {
	String name;
	boolean longName;
	int count;
	int buyPrice;
	public PlayerStock() {}
	public PlayerStock(String name, boolean longName,int count, int buyPrice) {
		this.name = name;
		this.longName = longName;
		this.count = count;
		this.buyPrice = buyPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public String getInfo(List<Stock> stocks) {
		int price=0;
		for(Stock s : stocks) {
			if(name.equals(s.getName()))
				price = count*s.getPrice();
		}
		
		double rate = ((double)price-buyPrice*count)/(buyPrice*count)*100;
		return String.format("\t%s\t%s%s\t     %,8d\t    %,8d\t      %,8d\t        %6.1f%%",
							name,longName?"":"\t",count,buyPrice,buyPrice*count,price,
							rate);
	}
}