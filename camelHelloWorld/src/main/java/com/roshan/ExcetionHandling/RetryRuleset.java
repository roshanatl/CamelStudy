package com.roshan.ExcetionHandling;

import org.apache.camel.component.http4.HttpOperationFailedException;
import org.apache.camel.Exchange;
import org.apache.camel.Header;

public class RetryRuleset {
	public boolean shouldRetry(@Header(Exchange.REDELIVERY_COUNTER) Integer counter, Exception causedBy) {
		
		HttpOperationFailedException httpE = (HttpOperationFailedException) causedBy;
		if(httpE.getStatusCode() == 500 && counter <3){
			return true;
		}
		return false;

	}
}