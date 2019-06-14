package com.hdi.integration.orchDeliveryDocument.service;

import com.hdi.integration.orchDeliveryDocument.dto.StarterKit;
import com.hdi.integration.orchDeliveryDocument.enumerator.EnumDeliveryDocumentType;
import com.hdi.integration.orchDeliveryDocument.exception.BusinnesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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

    public void validateDelivery(MultiValueMap headersMap, Long idInsurancePolicy) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(new ArrayList<>(Arrays.asList(MediaType.APPLICATION_JSON)));
        headers.addAll(headersMap);

        HttpEntity data = new HttpEntity<>(headers);

        ResponseEntity<Map> entity =
                restTemplate.exchange("http://localhost:8081/auto/insurancepolicy/v1/insurancepolicy/35556359/starterKitOptions",
                        HttpMethod.GET, data, Map.class);


        if (HttpStatus.OK.equals(entity.getStatusCode())) {
            System.out.println(entity.getBody());
        }
        else {
            // throw new BusinnesException(entity.getBody());
        }

    }

    @Async
    public Future<String> deliveryDocument(EnumDeliveryDocumentType documentType) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(this.uriBaseApiDeliveryDocuments, String.class, documentType);

        return AsyncResult.forValue(response.getBody());
    }

    @Async
    public Future<String> sendBoletoWithResult(String idInsurancePolicy) {
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
    public Future<String> sendCartaoWithResult(String idInsurancePolicy) {
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
    public Future<String> sendApoliceWithResult(String idInsurancePolicy) {
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
