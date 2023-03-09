package com.paka.pokemon.models;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -6005581272076110501L;

    private final String msgGroupReference;

    public ApiException(String message, String msgGroupReference) {
        super(message);
        this.msgGroupReference = msgGroupReference;
    }

    public String getMsgGroupReference() {
        return msgGroupReference;
    }
}
