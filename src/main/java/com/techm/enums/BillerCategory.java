package com.techm.enums;

public enum BillerCategory {
    ENTERTAINMENT("Entertainment"),
    TELECOM("Telecom"),
    INSURANCE("Insurance"),
    ELECTRICITY("Electricity"),
    WATER("Water"),
    GAS("Gas"),
    CABLE("Cable"),
    OTHER("Other");

    private final String value;

    BillerCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

