package com.roshan.ExcetionHandling;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpOperationFailedException;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelPagination {
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
					// Retry only for reason codes defined in RetryRuleset
					// eg:500
					onException(HttpOperationFailedException.class)
							.onWhen(method(ExceptionRuleset.class))
							.retryWhile(method(RetryRuleset.class))
							.redeliveryDelay(2); // Redelivery delay in ms

					// This route generate items input & call the
					// processItems Route
					from("timer://generateOrders?fixedRate=true&period=100000")
							.log("Generate order items")
							.process(new InputItemProcessor())
							.to("direct:processPages");

					from("direct:processPages")
							.log("processing  items ${body}")
							.process(new Processor() {
								public void process(Exchange exchange)
										throws Exception {
									//Initial page count
									exchange.setProperty("COUNT", 1);
									//This will have final results.Save in property
									List<Item> items = new ArrayList<Item>();
									exchange.setProperty("finalItems", items);
								}
							}).dynamicRouter(new Expression() {

								public <T> T evaluate(Exchange exchange,Class<T> type) {
									
									if (exchange.getProperty("COUNT",Integer.class) > 0)
										return (T) "direct:processItems";
									else
										return null;
								}
							})
							.log("Every thing done ${property.finalItems.size}");
					

					from("direct:processItems")
							.log(" Realy processing  items ${body}")
							.to("bean:itemListProcessor") // URL here 
							.log("processed  items ${body}")
							.process(new Processor() {
								
								public void process(Exchange exchange) throws Exception {									
									List<Item> items = exchange.getProperty("finalItems", List.class);
									Item item = exchange.getIn().getBody(Item.class);
									items.add(item);									
								}
							});     
					
				}
			});
			camelContext.start();
			Thread.sleep(500000);
		} finally {
			camelContext.stop();
		}
	}
}
