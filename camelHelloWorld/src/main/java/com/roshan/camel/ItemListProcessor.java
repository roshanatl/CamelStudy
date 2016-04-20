package com.roshan.camel;

import java.util.List;

public class ItemListProcessor {
    public void process(List<Item> item) {
    	for (Item orderItem :item ){
    		orderItem.process();
    	}
    }
}
