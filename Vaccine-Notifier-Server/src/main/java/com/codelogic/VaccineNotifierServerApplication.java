package com.codelogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VaccineNotifierServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineNotifierServerApplication.class, args);
	}

}
