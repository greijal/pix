package com.itau.pix.exception;

public class PixNoExistsException extends RuntimeException {
    private final Long id;

    public PixNoExistsException(Long id) {
        super("Pix ID no exist : " + id);
        this.id = id;
    }

    public Long getValue() {
        return id;
    }

}
