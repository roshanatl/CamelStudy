package com.roshan.ExcetionHandling;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpOperationFailedException;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelExceptionHandle {
	static Long startTime, endTime;

	public static final void main(String[] args) throws Exception {

		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("itemListProcessor", new ItemProcessor());
		jndiContext.bind("myRetryRuleset", new RetryRuleset());
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@SuppressWarnings("deprecation")
				public void configure() {
					//Retry only for reason codes defined in RetryRuleset eg:500
					onException(HttpOperationFailedException.class)
					.onWhen(method(ExceptionRuleset.class))//The onWhen predicate filter allows more fine-grained control over when an onException
					                                       //should be triggered.
					.retryWhile(method(RetryRuleset.class))//retryWhile to implement your own generic retry ruleset that determines
														   //how long it should retry
					.redeliveryDelay(2); //Redelivery delay in ms 
					
					

					// This route generate items input & call the
					// processItems Route
					from("timer://generateOrders?fixedRate=true&period=10000").log("Generate order items")
							.process(new InputItemProcessor()).to("direct:processItems");

					
             
					from("direct:processItems")
					.log("processing  items ${body}")
					.to("bean:itemListProcessor")
					.log("processed  items ${body}");

				}
			});
			camelContext.start();
			Thread.sleep(50000);
		} finally {
			camelContext.stop();
		}
	}
}
