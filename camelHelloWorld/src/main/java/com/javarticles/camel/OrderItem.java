package com.javarticles.camel;

public class OrderItem {
    private Order order;
    private String itemName;
    private int quantity;
    private int price;
    private boolean processed;
    
    public OrderItem(Order order, String itemName, int quantity, int price) {
        this.order = order;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public String getItemName() {
        return itemName;
    }
    
    public void process() {
    	try {
			Thread.sleep(10);
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
        return "Item[" + itemName + ", total price: " + (quantity * price) + ", processed: " + isProcessed() + "]";
    }
    
}
