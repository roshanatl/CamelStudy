package com.roshan.camel;

import java.util.ArrayList;
import java.util.List;

public class SplitterBean {
	 
    /**
     * The split body method returns something that is iteratable such as a java.util.List.
     *
     * @param body the payload of the incoming message
     * @return a list containing each part splitted
     */
	static int orderSpiltCount=0;
	static final int orderBatchSize=2;
    public List<List<OrderItem>> splitBody(Order order) {
        
       
        List<List<OrderItem>> listOfListOfSmallOrders = new ArrayList<List<OrderItem>>();
        
        int Start = orderSpiltCount*orderBatchSize;
        while(order.getItems().size() > Start)
        {
			List<OrderItem> smallOrders = new ArrayList<OrderItem>();
			smallOrders = order.getItems().subList(Start, ((Start + orderBatchSize) ));
			orderSpiltCount++;
			listOfListOfSmallOrders.add(smallOrders);
			Start = orderSpiltCount*orderBatchSize;
		}
        System.out.println("Splitted Orders : "+ listOfListOfSmallOrders);
        return listOfListOfSmallOrders;
    }
     
   
   
}
