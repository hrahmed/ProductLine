package com.productline.inventory;

import java.io.IOException;
import java.util.Properties;

public class InventoryService {
	String[] itemList;
	String[] itemStatus;

	public InventoryService() {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String itemListString = props.getProperty("itemList");
		itemList = itemListString.split(",");
		
		String itemStatusString = props.getProperty("itemStatus");
		itemStatus = itemStatusString.split(",");
	
	}
		
	public String checkItem(String itemId) throws InterruptedException{
				
		for (int i = 0; i < itemList.length; i++) {
			if (itemList[i].equalsIgnoreCase(itemId)) {
				Thread.sleep(2000);
				return itemStatus[i];
			}
		}
		
		return "In Stock";
		}
	
	
	public String reserveItem(String itemId) throws InterruptedException{
				
		for (int i = 0; i < itemList.length; i++) {
			if (itemList[i].equalsIgnoreCase(itemId)) {
				Thread.sleep(2000);
				return itemStatus[i];
			}
		}
		
		return "In Stock";
	}
	
	private void printItemList () {

		for (int i = 0; i < itemList.length; i++) {
			System.out.println("Item list is: " + itemList[i] + ':' + itemStatus[i]);
		}
	}
	
	public static void main(String[] args) {
		InventoryService is = new InventoryService();
		is.printItemList();
		
	}
}
