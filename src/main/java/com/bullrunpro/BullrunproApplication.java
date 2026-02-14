package com.bullrunpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BullrunproApplication {

	public static void main(String[] args) {
		SpringApplication.run(BullrunproApplication.class, args);
	}
}
