package com.roshan.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ItemListSplitProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		int spiltCount = 0;
		final int batchSize = 10;
		Order order = exchange.getIn().getBody(Order.class);
		List<List<Item>> listOfSmallList = new ArrayList<List<Item>>();
		int Start = spiltCount * batchSize;
		int end;
		int size = order.getItems().size();
		while (size > Start) {
			List<Item> smallList = new ArrayList<Item>();
			end = Start + batchSize;
			if (end > size) {
				end = size;
			}
			smallList = order.getItems().subList(Start, end);
			spiltCount++;
			listOfSmallList.add(smallList);
			Start = spiltCount * batchSize;
		}
		exchange.getIn().setBody(listOfSmallList);

	}

}
