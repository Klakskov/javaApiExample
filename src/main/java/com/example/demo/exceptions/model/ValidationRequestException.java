package com.example.demo.exceptions.model;

import lombok.Getter;

@Getter
public class ValidationRequestException extends RuntimeException {

    private final String userMsg;
    private static final long serialVersionUID = 8606264555565217548L;

    public ValidationRequestException(String message, String userMsg) {
        super(message);
        this.userMsg = userMsg;
    }

    public ValidationRequestException(String message, Throwable cause, String userMsg) {
        super(message, cause);
        this.userMsg = userMsg;
    }
}