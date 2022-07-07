package com.example.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends Exception {
    private String code;

    public UnauthorizedException(String message) {
        super(message);
        this.code = Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
    }
}
