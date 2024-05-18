package com.techm.enums;

public enum BillStatus {
    PAID("Paid"),
    PENDING("Pending"),
    OVERDUE("Overdue");

    private final String value;

    BillStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
