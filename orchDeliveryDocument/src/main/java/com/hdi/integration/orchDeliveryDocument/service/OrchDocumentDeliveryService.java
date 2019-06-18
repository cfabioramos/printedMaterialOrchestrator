package com.hdi.integration.orchDeliveryDocument.service;

import com.hdi.integration.orchDeliveryDocument.dto.InsurancePolicy;
import com.hdi.integration.orchDeliveryDocument.dto.StarterKit;
import com.hdi.integration.orchDeliveryDocument.dto.StarterKitOptions;
import com.hdi.integration.orchDeliveryDocument.enumerator.EnumDeliveryDocumentType;
import com.hdi.integration.orchDeliveryDocument.exception.BusinnesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class OrchDeliveryDocumentService {

    public OrchDeliveryDocumentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private static final Logger logger = LoggerFactory.getLogger(OrchDeliveryDocumentService.class);

    // local uri: http://localhost:8081/{documentPathIdentifier}/v1/request
    // @Value("${URI_BASE_DELIVERY_DOCU_API}")
    private String uriBaseApiDeliveryDocuments;

    RestTemplate restTemplate;

    public void validateDelivery(HttpHeaders headers, Long idInsurancePolicy, StarterKit documentDeliveryRequest) {

        List<StarterKitOptions> kitOptionsResponse = this.executeCallStartKitOptions(headers);

        System.out.println(kitOptionsResponse);

        if (kitOptionsResponse.containsAll(documentDeliveryRequest.getOptionsDelivery())) {
            InsurancePolicy insurancePolicy = this.executeCallEnrichmentPolicy(headers, idInsurancePolicy);

            System.out.println(insurancePolicy);
        }

    }

    private List<StarterKitOptions> executeCallStartKitOptions(HttpHeaders headers) {

        ResponseEntity<List<StarterKitOptions>> response =
                restTemplate.exchange("http://localhost:8081/auto/insurancepolicy/v1/insurancepolicy/35556359/starterKitOptions",
                        HttpMethod.GET, new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<StarterKitOptions>>() {
                        });

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return response.getBody();
        }
        else {
            throw new BusinnesException(response.getBody().toString());
        }
    }

    private InsurancePolicy executeCallEnrichmentPolicy(HttpHeaders headers, Long idInsurancePolicy) {

        ResponseEntity<InsurancePolicy> response =
                restTemplate.exchange("http://localhost:8082/auto/insurancepolicy/v1/insurancepolicy/35556359",
                        HttpMethod.GET, new HttpEntity<>(headers), InsurancePolicy.class);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return response.getBody();
        }
        else {
            throw new BusinnesException(response.getBody().toString());
        }
    }

    @Async
    public Future<String> deliveryDocument(EnumDeliveryDocumentType documentType) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(this.uriBaseApiDeliveryDocuments, String.class, documentType);

        return AsyncResult.forValue(response.getBody());
    }

    public Long getIdInsurancePolicy(Long idInsurancePolicy) {
        this.logger.info("> obteve o id...");
        System.out.println("> obteve o id...");
        return idInsurancePolicy;
    }

    @Async
    public Future<String> sendBoletoWithResult(Long idInsurancePolicy) {
        this.logger.info("> send Boleto...");
        System.out.println("> send Boleto...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AsyncResult.forValue("Boleto OK");
    }

    @Async
    public Future<String> sendCartaoWithResult(Long idInsurancePolicy) {
        this.logger.info("> send Cartao...");
        System.out.println("> send Cartao...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AsyncResult.forValue("Cartao OK");
    }

    @Async
    public Future<String> sendApoliceWithResult(Long idInsurancePolicy) {
        this.logger.info("> send Apolice...");
        System.out.println("> send Apolice...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AsyncResult.forValue("Apolice OK");
    }

}
