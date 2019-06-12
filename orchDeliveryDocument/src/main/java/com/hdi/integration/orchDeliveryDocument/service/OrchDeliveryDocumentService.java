package com.hdi.integration.orchDeliveryDocument.service;

import com.hdi.integration.orchDeliveryDocument.enumerator.EnumDeliveryDocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class OrchDeliveryDocumentService {

    // local uri: http://localhost:8081/{documentPathIdentifier}/v1/request
    @Value("${URI_BASE_DELIVERY_DOCU_API}")
    private String uriBaseApiDeliveryDocuments;

    @Autowired
    RestTemplate restTemplate;

    @Async
    public Future<String> deliveryDocument(EnumDeliveryDocumentType documentType) {
        ResponseEntity<String> response =
                restTemplate.getForEntity(this.uriBaseApiDeliveryDocuments, String.class, documentType);

        return AsyncResult.forValue(response.getBody());
    }

}
