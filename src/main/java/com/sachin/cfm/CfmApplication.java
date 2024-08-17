package com.sachin.cfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfmApplication.class, args);
	}

}
