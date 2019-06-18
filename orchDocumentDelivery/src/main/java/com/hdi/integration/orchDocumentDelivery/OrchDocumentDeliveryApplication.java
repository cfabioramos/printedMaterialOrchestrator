package com.hdi.integration.orchDocumentDelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrchDocumentDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchDocumentDeliveryApplication.class, args);
	}

}
