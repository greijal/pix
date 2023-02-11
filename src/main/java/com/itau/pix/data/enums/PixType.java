package com.itau.pix.data.enums;

public enum PixType {
    CELULAR("CELULAR"),
    EMAIL("EMAIL"),
    CPF("CPF"),
    CNPJ("CNPJ"),
    ALEATORIO("ALEATORIO");

    private final String value;

    PixType(String value) {
        this.value = value;
    }

    public static PixType fromString(String value) {
        for (PixType p : PixType.values()) {
            if (p.value.equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
