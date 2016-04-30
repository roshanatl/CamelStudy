package com.roshan.SplitAndAggregate;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> orderItems = new ArrayList<Item>();

    private int number;

    public Order(int number) {
        this.number = number;
    }

    public void addItem(String itemName, int quantity, int price) {
        this.orderItems.add(new Item(this,itemName, quantity, price));
    }

    public int getNumber() {
        return number;
    }

    public List<Item> getItems() {
        return this.orderItems;
    }
    
    public String toString() {
        return "Order: " + number + ", Items: " + orderItems;
    }
}
