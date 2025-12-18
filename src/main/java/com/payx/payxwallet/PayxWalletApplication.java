package com.payx.payxwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class PayxWalletApplication {

	private static final Logger logger = LoggerFactory.getLogger(PayxWalletApplication.class);

	public static void main(String[] args) {
		logger.info("Starting PayxWalletApplication...");
		SpringApplication.run(PayxWalletApplication.class, args);
		logger.info("PayxWalletApplication started successfully.");
	}

}