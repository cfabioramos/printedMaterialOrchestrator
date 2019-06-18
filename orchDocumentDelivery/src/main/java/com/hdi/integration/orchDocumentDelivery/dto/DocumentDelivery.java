package com.hdi.integration.orchDocumentDelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Document Delivery Request")
public class DocumentDelivery {
    @ApiModelProperty(value = "Insurance Policy Code: format compayCode.branchCode.productCode.typeID.documentCode.endorsmentCode (00.000.000.A/P.000000.000000)")
    private String insurancePolicyCode;
    @ApiModelProperty(value = "Insurance Policy Item ID")
    private Integer itemId;
    @ApiModelProperty(value = "User Login request")
    private String userLogin;
    private Address address;
    private OptionsDelivery optionsDelivery;

    public String getInsurancePolicyCode() {
        return insurancePolicyCode;
    }

    public void setInsurancePolicyCode(String insurancePolicyCode) {
        this.insurancePolicyCode = insurancePolicyCode;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OptionsDelivery getOptionsDelivery() {
        return optionsDelivery;
    }

    public void setOptionsDelivery(OptionsDelivery optionsDelivery) {
        this.optionsDelivery = optionsDelivery;
    }
}