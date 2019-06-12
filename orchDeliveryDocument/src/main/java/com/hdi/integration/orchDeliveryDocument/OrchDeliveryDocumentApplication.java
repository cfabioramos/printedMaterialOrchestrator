package com.hdi.integration.orchDeliveryDocument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrchDeliveryDocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchDeliveryDocumentApplication.class, args);
	}

}
