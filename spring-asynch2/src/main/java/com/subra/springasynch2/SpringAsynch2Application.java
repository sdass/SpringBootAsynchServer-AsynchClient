package com.subra.springasynch2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableAsync
public class SpringAsynch2Application {

	static Logger log = LoggerFactory.getLogger(SpringAsynch2Application.class);
	public static void main(String[] args) {
		log.info("App begings . . .");
		SpringApplication.run(SpringAsynch2Application.class, args);
	}

}
