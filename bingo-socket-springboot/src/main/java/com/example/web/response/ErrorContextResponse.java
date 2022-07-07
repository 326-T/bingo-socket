package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorContextResponse {
    private Integer status;
    private String code;
    private String message;
}
