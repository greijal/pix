package com.itau.pix.exception;

public class PixAlreadyExistsException extends RuntimeException {
    private final String value;

    public PixAlreadyExistsException(String value) {
        super("Pix value already exist : " + value);
        this.value = value;
    }

}
