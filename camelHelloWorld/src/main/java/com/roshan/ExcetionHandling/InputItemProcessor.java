package com.roshan.ExcetionHandling;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InputItemProcessor implements Processor {
	static int count = 0;

	public void process(Exchange exchange) throws Exception {
	   count++;	 
       Item item = new Item("COKE", count, 5, new Date());
        
        exchange.getIn().setBody(item);
		
	}

}
