package com.hdi.integration.orchDocumentDelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Address Entity")
public class Address {
    @ApiModelProperty(value = "Address Reference (Payment, home, etc)")
    private String name;
    @ApiModelProperty(value = "street")
    private String streetName;
    @ApiModelProperty(value = "number")
    private Integer number;
    @ApiModelProperty(value = "Complement")
    private String complement;
    @ApiModelProperty(value = "Neighborhood")
    private String district;
    private City city;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
