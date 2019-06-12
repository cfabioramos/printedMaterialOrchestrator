package com.hdi.integration.orchDeliveryDocument.controller;

import com.hdi.integration.orchDeliveryDocument.dto.StarterKit;
import com.hdi.integration.orchDeliveryDocument.service.OrchDeliveryDocumentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrchDeliveryDocumentController {

    @Autowired
    private OrchDeliveryDocumentService service;

    @ApiOperation(value = "Orchestrator for documents delivery")
    @PostMapping(value = "/insurancepolicy/{idInsurancePolicy}/orchDeliveryDocument")
    public ResponseEntity<?> orchDeliveryDocument(
            @RequestHeader(value = "X-Company-Id", required = true) String companyId,
            @RequestHeader(value = "X-Application-Id", required = true) String applicationId,
            @RequestHeader(value = "X-User-Id", required = true) String userId,
            @ApiParam(value = "Insurance Policy Id", required = true) @PathVariable("idInsurancePolicy") String idInsurancePolicy,
            @RequestBody @Valid StarterKit documentDeliveryRequest) throws IOException {

        Map<String, String> param = new HashMap<>();
        param.put("X-Company-Id", companyId);
        param.put("X-Application-Id", applicationId);
        param.put("X-User-Id", userId);

        // TODO 1: Busca lista de documentos com base no idInsurancePolicy

        // TODO 2: Se as opcoes informadas (documentDeliveryRequest.optionsDelivery) estao contidas na listagem acima

        //TODO 3: Enriquece...

        /*
            TODO 4: Os detalhes acima podem estar no service. Que:
             (i) retorna um Objeto Enriquecido
             (ii) lança um erro caso não esteja
         */

        //TODO 4: Retornando um objeto enriquecido, faz as chamadas assíncronas...

//        return service.execute(idInsurancePolicy, documentDeliveryRequest, param);

        return null;
    }


}
