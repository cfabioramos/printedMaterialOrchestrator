package com.hdi.integration.orchDocumentDelivery.controller;

import com.hdi.integration.orchDocumentDelivery.dto.DocumentDelivery;
import com.hdi.integration.orchDocumentDelivery.service.OrchDocumentDeliveryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class OrchDocumentDeliveryController {

    @Autowired
    private OrchDocumentDeliveryService service;

    @ApiOperation(value = "Orchestrator for documents delivery")
    @PostMapping(value = "/insurancepolicy/{idInsurancePolicy}/orchDocumentDelivery", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<String> orchDocumentDelivery(
            @RequestHeader(value = "X-Company-Id", required = true) String companyId,
            @RequestHeader(value = "X-Application-Id", required = true) String applicationId,
            @RequestHeader(value = "X-User-Id", required = true) String userId,
            @ApiParam(value = "Insurance Policy Id", required = true) @PathVariable("idInsurancePolicy") Long idInsurancePolicy,
            @RequestBody DocumentDelivery documentDeliveryRequest) {

        System.out.println(documentDeliveryRequest);

        return this.service.processDocumentDelivery(this.getHeaders(companyId, applicationId, userId), idInsurancePolicy, documentDeliveryRequest);

        // return service.execute(idInsurancePolicy, documentDeliveryRequest, headerParams);

//        idInsurancePolicy = this.service.getIdInsurancePolicy(idInsurancePolicy);
//
//        try {
//            Future<String> future1 = service.sendBoletoWithResult(idInsurancePolicy);
//            Future<String> future2 = service.sendCartaoWithResult(idInsurancePolicy);
//            Future<String> future3 = service.sendApoliceWithResult(idInsurancePolicy);
//
//            List<String> lista = new ArrayList<>();
//
//            lista.add(future1.get());
//            lista.add(future2.get());
//            lista.add(future3.get());
//
//            return new ResponseEntity<>(lista, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private HttpHeaders getHeaders(String companyId, String applicationId, String userId) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(new ArrayList<>(Arrays.asList(MediaType.APPLICATION_JSON)));
        headers.add("X-Company-Id", companyId);
        headers.add("X-Application-Id", applicationId);
        headers.add("X-User-Id", userId);

        return headers;
    }


}
