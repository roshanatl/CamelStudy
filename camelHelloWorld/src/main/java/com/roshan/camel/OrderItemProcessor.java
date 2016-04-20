package com.roshan.camel;

import java.util.List;

public class OrderItemProcessor {
    public void process(List<OrderItem> item) {
    	for (OrderItem orderItem :item ){
    		orderItem.process();
    	}
    }
}
