package com.techm.enums;

public enum ProductCategory {
	ENTERTAINMENT("Entertainment"),
    TELECOM("Telecom"),
    INSURANCE("Insurance"),
    ELECTRICITY("Electricity"),
    WATER("Water"),
    GAS("Gas"),
    CABLE("Cable"),
    OTHER("Other");
	
	private final String value;

	ProductCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
