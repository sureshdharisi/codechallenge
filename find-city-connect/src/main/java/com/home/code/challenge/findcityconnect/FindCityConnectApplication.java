package com.home.code.challenge.findcityconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * It is the place from where application will start.
 * 
 * @author dsuresh
 *
 */
@SpringBootApplication
@EnableSwagger2
public class FindCityConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindCityConnectApplication.class, args);
	}

}
