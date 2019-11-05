package com.subra.springasynch2;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	Logger log = LoggerFactory.getLogger(HelloService.class);
	
	@Async
	CompletableFuture<String> sayHelloWithFuture() {

		log.info("01 HelloService Future . . . called");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Hello web in Future asynch");

		log.info("02 HelloService Future . . . returning");
		return completedFuture;

	}
}
