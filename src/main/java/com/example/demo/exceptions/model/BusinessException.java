package com.example.demo.exceptions.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 8606264555565217548L;

    private final String userMsg;
    public BusinessException(String message, String userMsg) {
        super(message);
        this.userMsg = userMsg;
    }

    public BusinessException(String message, Throwable cause, String userMsg) {
        super(message, cause);
        this.userMsg = userMsg;
    }
}