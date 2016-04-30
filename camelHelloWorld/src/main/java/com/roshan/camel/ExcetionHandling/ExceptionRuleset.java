package com.roshan.camel.ExcetionHandling;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.component.http4.HttpOperationFailedException;

public class ExceptionRuleset {
public boolean shouldHandle(@Header(Exchange.REDELIVERY_COUNTER) Integer counter, Exception causedBy) {
		
		HttpOperationFailedException httpE = (HttpOperationFailedException) causedBy;
		if(httpE.getStatusCode() == 500 ){
			return true;
		}
		return false;

	}

}
