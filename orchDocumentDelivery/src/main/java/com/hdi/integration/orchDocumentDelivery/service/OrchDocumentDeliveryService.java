package com.hdi.integration.orchDocumentDelivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdi.integration.orchDocumentDelivery.dto.DocumentDelivery;
import com.hdi.integration.orchDocumentDelivery.dto.DocumentDeliveryOptions;
import com.hdi.integration.orchDocumentDelivery.dto.policyEnrichment.InsurancePolicy;
import com.hdi.integration.orchDocumentDelivery.enumerator.EnumDocumentDeliveryType;
import com.hdi.integration.orchDocumentDelivery.exception.BusinnesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class OrchDocumentDeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(OrchDocumentDeliveryService.class);

    public OrchDocumentDeliveryService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.paramUriVariables = new HashMap<>();
    }

    private Map<String, Long> paramUriVariables;

    @Value("${URI_BASE_DOCUMENT_DELIVERY_OPTIONS_API}")
    private String uriBaseDocumentDeliveryOptionsApi;

    @Value("${URI_BASE_ENRICHMENT_POLICY_API}")
    private String uriBaseEnrichmentPolicyApi;

    @Value("${URI_BASE_DOCUMENT_DELIVERY_POLICY_API}")
    private String uriBaseDocumentDeliveryPolicyApi;

    @Value("${URI_BASE_DOCUMENT_DELIVERY_TICKET_API}")
    private String uriBaseDocumentDeliveryTicketApi;

    @Value("${URI_BASE_DOCUMENT_DELIVERY_CARD_API}")
    private String uriBaseDocumentDeliveryCardApi;

    RestTemplate restTemplate;

    public ResponseEntity<String> processDocumentDelivery(
            HttpHeaders headers, Long idInsurancePolicy, DocumentDelivery documentDeliveryRequest) {

        DocumentDelivery documentDelivery = this.validateDelivery(headers, idInsurancePolicy, documentDeliveryRequest);

        if ( EnumDocumentDeliveryType.POLICY.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId()) ||
                EnumDocumentDeliveryType.POLICY_A.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId())) {

            return this.executeCallDelivery(uriBaseDocumentDeliveryPolicyApi, headers, documentDelivery);
        }
        else if ( EnumDocumentDeliveryType.CARD.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId()) ||
                EnumDocumentDeliveryType.CARD_A.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId()) ||
                EnumDocumentDeliveryType.CARD_B.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId())) {

            return this.executeCallDelivery(uriBaseDocumentDeliveryCardApi, headers, documentDelivery);
        }
        else if ( EnumDocumentDeliveryType.TICKET.getId().equals(documentDeliveryRequest.getOptionsDelivery().getId())) {

            return this.executeCallDelivery(uriBaseDocumentDeliveryTicketApi, headers, documentDelivery);
        }
        else {
            throw new BusinnesException("Documento solicitado não permitido para apólice informada.");
        }

    }

    private DocumentDelivery validateDelivery(HttpHeaders headers, Long idInsurancePolicy, DocumentDelivery documentDeliveryRequest) {

        this.paramUriVariables.put("idInsurancePolicy", idInsurancePolicy);

        List<DocumentDeliveryOptions> kitOptionsResponse = this.executeCallDocumentDeliveryOptions(headers, idInsurancePolicy);

        if (kitOptionsResponse.contains(documentDeliveryRequest.getOptionsDelivery())) {

            InsurancePolicy insurancePolicyDetails = this.executeCallEnrichmentPolicy(headers, idInsurancePolicy);

            documentDeliveryRequest.setInsurancePolicyCode(insurancePolicyDetails.getInsurancePolicyNumber());
            insurancePolicyDetails.getCustomer().getDocuments().forEach(document -> {
                if ("0".equals(document.getType().getId()))
                    documentDeliveryRequest.setCpfCgc(document.getNumber());
            });
            System.out.println(insurancePolicyDetails);
            return documentDeliveryRequest;
        }

        throw new BusinnesException("Documento solicitado não permitido para apólice informada.");

    }

    private List<DocumentDeliveryOptions> executeCallDocumentDeliveryOptions(HttpHeaders headers, Long idInsurancePolicy) {

        this.paramUriVariables.put("idInsurancePolicy", idInsurancePolicy);

        ResponseEntity<List<DocumentDeliveryOptions>> response =
                restTemplate.exchange(this.uriBaseDocumentDeliveryOptionsApi, HttpMethod.GET, new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<DocumentDeliveryOptions>>() {}, this.paramUriVariables);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return response.getBody();
        }
        else {
            throw new BusinnesException(response.getBody().toString());
        }
    }

    private InsurancePolicy executeCallEnrichmentPolicy(HttpHeaders headers, Long idInsurancePolicy) {

        ResponseEntity<InsurancePolicy> response =
                restTemplate.exchange(this.uriBaseEnrichmentPolicyApi, HttpMethod.GET, new HttpEntity<>(headers),
                        InsurancePolicy.class, this.paramUriVariables);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return response.getBody();
        }
        else {
            throw new BusinnesException(response.getBody().toString());
        }
    }

    private ResponseEntity<String> executeCallDelivery(String uriDelivery, HttpHeaders headers, DocumentDelivery documentDelivery) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = null;
        try {
            entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(documentDelivery), headers);
            return restTemplate.exchange(uriDelivery, HttpMethod.PUT, entity, String.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinnesException(e.getMessage());
        }
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
