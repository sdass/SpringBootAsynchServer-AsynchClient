package com.subra.springasynchclient;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class HelloClientController {

	private static final String BASE_URL = "http://localhost:8080/";
	private Logger log = LoggerFactory.getLogger(HelloClientController.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	
	@RequestMapping("/getfuture")
	public String consumeAsynch(){
		log.info("Entered /getfuture");
		String sUrl = new StringBuilder(BASE_URL).append("asynchFutureHello").toString();
		log.info(sUrl);
		String received = restTemplate.getForObject(sUrl, String.class);
		log.info("received=" + received);
		return "consumed from:" + received;
	}
	
	@RequestMapping("/asynchgetfuture")
	public DeferredResult<String> consumeAsynchReal() throws InterruptedException, ExecutionException{
		log.info("Entered /asynchgetfuture");
		String sUrl = new StringBuilder(BASE_URL).append("asynchFutureHello").toString();
		log.info(sUrl);		
		//4 steps prepare before sending calling
		
		//String rcvdResult = ""; from anonymous class access NO because not effectively final 
		//1 declare result container. avoid anonymous effectively final
		DeferredResult<String> deferedResult = new DeferredResult<String>();
		//2 regular call setting input output
		ListenableFuture<ResponseEntity<String>> futureResponse = asyncRestTemplate.getForEntity(sUrl, String.class);
		//3 success-error handler		
		ListenableFutureCallback<ResponseEntity<String>> listenAbleFutureCallback = new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onSuccess(ResponseEntity<String> result) {
				log.info("-------- success handler -----");
				 String s = result.getBody();
				 s = s + " callback-success";
				 deferedResult.setResult(s);	
				 log.info("Inside success handler -- >>>" + deferedResult.getResult());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("-------- failure handler -----");
				ex.printStackTrace();
				deferedResult.setErrorResult(ex.getMessage());
				
			}
		};
		//4. hook the handler
		futureResponse.addCallback(listenAbleFutureCallback);
				
		log.info("too early like ajax call -- >>>" + deferedResult.getResult());
		log.info("Can do other processing... callBackmethods(success|failure) receives result");
		return deferedResult;
	}
}
