package com.sejong.errorhandlertemplate.application.error.exceptionHandler;


import com.sejong.errorhandlertemplate.application.error.api.ErrorResponse;
import com.sejong.errorhandlertemplate.application.error.code.ErrorCodeIfs;
import com.sejong.errorhandlertemplate.application.error.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden //swagger build gradle 에 넣어야 동작되는 코드입니다
@RestControllerAdvice
@Order(value=Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> apiException(
            ApiException apiException
    ){
        ErrorCodeIfs errorCode = apiException.getErrorCodeIfs();
        ErrorResponse errorResponse = ErrorResponse.ERROR(errorCode, apiException.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(errorResponse);
    }
}
