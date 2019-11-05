package com.subra.springasynchclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringAsynchClientApplication {

	private static Logger log = LoggerFactory.getLogger(SpringAsynchClientApplication.class);
	
	public static void main(String[] args) {
		log.info("consuming asynch rest call");
		SpringApplication.run(SpringAsynchClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AsyncRestTemplate asynchRestTemplate() {
		return new AsyncRestTemplate();
	}
	
	
		
		
	
}
