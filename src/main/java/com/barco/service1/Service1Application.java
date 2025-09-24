package com.barco.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nabeel Ahmed
 */
@SpringBootApplication
public class Service1Application {

	private Logger logger = LoggerFactory.getLogger(Service1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

}
