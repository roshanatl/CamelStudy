package com.roshan.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelSplitterExample {
	static Long startTime, endTime;

	public static final void main(String[] args) throws Exception {

		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("itemListProcessor", new ItemListProcessor());		
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new RouteBuilder() {
				public void configure() {

					// This route generate order with items input & call the
					// processOrder Route
					from("timer://generateOrders?fixedRate=true&period=10000").log("Generate order items")
							.process(new InputProcessor()).to("direct:processOrder");

					// This Route logs the start time,Split the items into
					// small lists if size 10
					// Split the input in group of 10 & call the
					// processOrderitem route
					// The aggregator joins the results back.
					from("direct:processOrder").log("Process order ${body}")
					
					.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							startTime = System.currentTimeMillis();							
						}
					})
					.process(new ItemListSplitProcessor())
					.split(body(), new OrderAggregationStrategy()).parallelProcessing()					
					.to("direct:processItems").end()
					
					.log("Order processed: ${body.size}").process(new Processor() {

						public void process(Exchange exchange) throws Exception {
							endTime = System.currentTimeMillis();
							System.out.println("Time Taken = " + (endTime - startTime));

						}
					});

					// This route invoke process on each item in the ItemList of
					// size 10

					from("direct:processItems").log("processing  items ${body}").to("bean:itemListProcessor");

				}
			});
			camelContext.start();
			Thread.sleep(5000);
		} finally {
			camelContext.stop();
		}
	}
}
