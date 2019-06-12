package com.hdi.integration.orchDeliveryDocument.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class StarterKit implements Serializable {

    @ApiModelProperty(value = "If it want Printed options for starter kit")
    private String isPrint;
    private Address address;
    @ApiModelProperty(value = "Another email contact")
    private String email;
    @JsonProperty("optionsDelivery")
    private List<OptionsDelivery> optionsDelivery;

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OptionsDelivery> getOptionsDelivery() {
        return optionsDelivery;
    }

    public void setOptionsDelivery(List<OptionsDelivery> optionsDelivery) {
        this.optionsDelivery = optionsDelivery;
    }
}
