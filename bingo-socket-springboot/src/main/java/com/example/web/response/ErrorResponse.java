package com.example.web.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private ErrorContextResponse error;

    @JsonCreator
    public ErrorResponse(Integer status, String code, String message) {
        this.error = new ErrorContextResponse(status, code, message);
    }
}

