package com.example.exception;

import com.example.web.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerException(UnauthorizedException e) {
        logger.error(e.getMessage(), e);
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "認証されていません。", e.getMessage());
    }
}
