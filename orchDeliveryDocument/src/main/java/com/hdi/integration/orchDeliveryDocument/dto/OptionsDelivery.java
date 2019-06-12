package com.hdi.integration.orchDeliveryDocument.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class OptionsDelivery implements Serializable  {
    @ApiModelProperty(value = "Id of the Delivery Document")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
