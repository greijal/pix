package com.itau.pix.data.enums;

public enum AccountType {
    CORRENTE("CORRENTE"),
    POUPANCA("POUPANCA");

    private final String value;


    AccountType(String value) {
        this.value = value;
    }

    public static AccountType fromString(String value) {
        for (AccountType t : AccountType.values()) {
            if (t.value.equalsIgnoreCase(value)) {
                return t;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
