package com.roshan.ExcetionHandling;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.component.http4.HttpOperationFailedException;

public class ExceptionRuleset {
public boolean shouldHandle(@Header(Exchange.REDELIVERY_COUNTER) Integer counter, Exception causedBy) {
		
		HttpOperationFailedException httpE = (HttpOperationFailedException) causedBy;
		//We are interested only in status code 500.
		if(httpE.getStatusCode() == 500 ){
			return true;
		}
		return false;

	}

}
