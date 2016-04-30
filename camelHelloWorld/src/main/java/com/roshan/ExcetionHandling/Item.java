package com.roshan.ExcetionHandling;

import java.util.Date;

public class Item {
    
    private String itemName;
    private int quantity;
    private int price;
    private boolean processed;
    private Date date;
    private int retry;
    
    public int getRetry() {
		return retry;
	}



	public void setRetry(int retry) {
		this.retry = retry;
	}



	public Item(String itemName, int quantity, int price,Date date) {
      
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.setDate(date);
        this.retry=0;
    }

    

    public String getItemName() {
        return itemName;
    }
    
    public void process() {
    	try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        processed = true;
    }

    public boolean isProcessed() {
        return processed;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
    
    public String toString() {
        return "Item[" + itemName + ", total price: " + (quantity * price) + ", processed: " + isProcessed() + ",date:"+ date + ",processTry:" + retry + "]";
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public void incRetry() {
		retry++;
		
	}
    
}
