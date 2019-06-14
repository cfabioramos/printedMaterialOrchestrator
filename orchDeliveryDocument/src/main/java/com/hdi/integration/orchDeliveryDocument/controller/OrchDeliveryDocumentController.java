package com.hdi.integration.orchDeliveryDocument.controller;

import com.hdi.integration.orchDeliveryDocument.dto.StarterKit;
import com.hdi.integration.orchDeliveryDocument.service.OrchDeliveryDocumentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
public class OrchDeliveryDocumentController {

    @Autowired
    private OrchDeliveryDocumentService service;

    @ApiOperation(value = "Orchestrator for documents delivery")
    @PostMapping(value = "/insurancepolicy/{idInsurancePolicy}/orchDeliveryDocument", produces = {"application/json"})
    public ResponseEntity<?> orchDeliveryDocument(
            @RequestHeader(value = "X-Company-Id", required = true) String companyId,
            @RequestHeader(value = "X-Application-Id", required = true) String applicationId,
            @RequestHeader(value = "X-User-Id", required = true) String userId,
            @ApiParam(value = "Insurance Policy Id", required = true) @PathVariable("idInsurancePolicy") Long idInsurancePolicy) throws IOException {

        MultiValueMap<String, String> headerParams = new LinkedMultiValueMap<String, String>();
        headerParams.add("X-Company-Id", companyId);
        headerParams.add("X-Application-Id", applicationId);
        headerParams.add("X-User-Id", userId);
//        headerParams.put("Accept", "application/json");

        this.service.validateDelivery(headerParams, idInsurancePolicy);

        // TODO 1: Busca lista de documentos com base no idInsurancePolicy

        // TODO 2: Se as opcoes informadas (documentDeliveryRequest.optionsDelivery) estao contidas na listagem acima

        // TODO 3: Enriquece

        /*
            TODO 4: Os detalhes acima podem estar no service. Que:
             (i) retorna um Objeto Enriquecido
             (ii) lança um erro caso não esteja
         */

        // TODO 4: Retornando um objeto enriquecido, faz as chamadas assíncronas...

        // return service.execute(idInsurancePolicy, documentDeliveryRequest, headerParams);

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


        return null;
    }


}
