package com.jay.accounts.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.jay.accounts.accountservice")
public class AccountServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
