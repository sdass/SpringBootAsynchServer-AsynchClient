package com.subra.springasynch2;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloController {
	Logger log = LoggerFactory.getLogger(HeloController.class);
	
	@Autowired HelloService hservice;
	
	@RequestMapping("/helo")
	public String sayHelo(){
		log.info("/hello hits 0.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Hello in web";
	}
	
	@RequestMapping("/asynchHello")
	public Callable<String> sayAsynchHelo(){
		log.info("/asynchHello hits 1");
		
		Callable<String> asynchTask = new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "Hello in web in asynch";
			}			
		};
		
		return asynchTask;	//http://localhost:8080/asynchHello receives the string on return statement	
	}


	
	@Async
	@RequestMapping("/asynchFutureHello")
	public CompletableFuture<String> sayAsynchFutureHelo(){
		log.info("/asynchFutureHello hits 2");
		
		CompletableFuture<String> resp = hservice.sayHelloWithFuture();
		log.info("1");
		log.info("2");

		return resp;
		/*
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Hello in web in asynch");	
		return completedFuture;	//http://localhost:8080/asynchHello receives the string on return statement
		*/	
	}
	
}
