package com.hdi.integration.orchDeliveryDocument.enumerator;

public enum EnumDeliveryDocumentType {

    POLICY(1, "deliveryticket"),
    CARD(2, "deliverycard"),
    TICKET(3, "deliverypolicy"),
    KIT(4, "");

    EnumDeliveryDocumentType(int code, String pathIdentifier) {
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
