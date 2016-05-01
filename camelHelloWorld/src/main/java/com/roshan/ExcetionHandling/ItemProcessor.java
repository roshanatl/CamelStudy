package com.roshan.ExcetionHandling;

import java.io.IOException;
import java.util.List;

import org.apache.camel.component.http4.HttpOperationFailedException;


public class ItemProcessor {
	static int count = 0;
    public void process(Item item) throws IOException, HttpOperationFailedException {
    	count++;
    	item.incRetry();
    	if(item.getQuantity() == 1 && item.getRetry() <2 )
    	{
    		System.out.println("HttpOperationFailedException********************************");
    		throw new HttpOperationFailedException("hello", 500, "failed", null, null, null);
    	}
//    	if(count==2)
//    	{
//    		System.out.println("IOException********************************");
//    		throw new HttpOperationFailedException("hello", 400, "failed", null, null, null);
//    	}
    	
    	item.process();
    }
}
