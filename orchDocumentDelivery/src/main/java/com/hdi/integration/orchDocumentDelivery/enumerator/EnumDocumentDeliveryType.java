package com.hdi.integration.orchDocumentDelivery.enumerator;

public enum EnumDocumentDeliveryType {

    ALL(1, "deliveryall"),
    POLICY(2, "deliveryticket"),
    CARD(3, "deliverycard"),
    POLICY_A(4, "deliveryticketa"),
    CARD_A(5, "deliverycarda"),
    CARD_B(6, "deliverycardb"),
    KIT(7, "");

    EnumDocumentDeliveryType(int code, String pathIdentifier) {
        this.code = code;
        this.pathIdentifier = pathIdentifier;
    }

    private int code;
    private String pathIdentifier;

    public int getCode() {
        return code;
    }
    public String getPathIdentifier() {
        return pathIdentifier;
    }

}
