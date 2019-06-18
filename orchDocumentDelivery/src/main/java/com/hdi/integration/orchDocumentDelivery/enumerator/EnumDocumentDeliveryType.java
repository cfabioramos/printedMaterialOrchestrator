package com.hdi.integration.orchDocumentDelivery.enumerator;

public enum EnumDocumentDeliveryType {

    ALL(1L, "deliveryall"),
    POLICY(2L, "deliverypolicy"),
    CARD(3L, "deliverycard"),
    POLICY_A(4L, "deliverypolicy"),
    CARD_A(5L, "deliverycard"),
    CARD_B(6L, "deliverycard"),
    TICKET(7L, "deliveryticket");

    EnumDocumentDeliveryType(Long id, String pathIdentifier) {
        this.id = id;
        this.pathIdentifier = pathIdentifier;
    }

    private Long id;
    private String pathIdentifier;

    public Long getId() {
        return id;
    }
    public String getPathIdentifier() {
        return pathIdentifier;
    }

}
