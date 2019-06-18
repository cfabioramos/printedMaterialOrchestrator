package com.hdi.integration.orchDocumentDelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "Options Delivery")
public class OptionsDelivery {
    @ApiModelProperty(value = "Id of the Delivery Document")
    private Long id;
    @ApiModelProperty(value = "Id reason for Delivery Document")
    private Integer reasonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || (getClass() != o.getClass() && DocumentDeliveryOptions.class != o.getClass())) {
            return false;
        }
        if (getClass() == o.getClass()) {
            OptionsDelivery optionsDelivery = (OptionsDelivery) o;
            return Objects.equals(this.id, optionsDelivery.id);
        }
        if (DocumentDeliveryOptions.class == o.getClass()) {
            DocumentDeliveryOptions documentDeliveryOptions = (DocumentDeliveryOptions) o;
            return Objects.equals(this.id, documentDeliveryOptions.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OptionsDelivery{" +
                "id=" + id +
                ", reasonId=" + reasonId +
                '}';
    }
}
