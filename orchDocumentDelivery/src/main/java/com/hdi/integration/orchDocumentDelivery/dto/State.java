package com.hdi.integration.orchDocumentDelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "State Entity")
public class State {

    @ApiModelProperty(value = "name description")
    private String name;
    @ApiModelProperty(value = "Country Name")
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
