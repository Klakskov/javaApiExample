package com.example.demo.config.log;

import lombok.Getter;

class RemoveFromLog {

    @Getter
    public static final String patternRemove = "(password)" +
            "|(sensitiveData)" +
            "";
}
