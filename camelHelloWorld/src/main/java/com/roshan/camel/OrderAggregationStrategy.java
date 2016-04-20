package com.roshan.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class OrderAggregationStrategy implements AggregationStrategy {
	 
	 @SuppressWarnings("unchecked")
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		 
		 List<Item> finalList;
         Exchange resultExchange;
         if (oldExchange == null) {
        	 finalList = new ArrayList<Item>();
             resultExchange = newExchange;
         } else {
        	 finalList = oldExchange.getIn().getBody(List.class);
             resultExchange = oldExchange;
         }

         if (null != newExchange.getIn().getBody(List.class)) {
        	 finalList.addAll(newExchange.getIn().getBody(List.class));
             resultExchange.getIn().setBody(finalList, List.class);
         }

         return resultExchange;
         
	   
	    }
 
}