package com.techm.enums;

public enum Role {
    CUSTOMER("Customer"),
    BILLER("Biller");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
