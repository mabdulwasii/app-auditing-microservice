package com.simplifysynergy.cqrs.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountCommandApplication.class, args);
	}

}
