package com.efub_assignment.community.community.global.exception;

import org.springframework.http.HttpStatusCode;

public class communityException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public communityException(ExceptionCode exceptionCode){
        this.exceptionCode = exceptionCode;
    }

    @Override
    public String getMessage() {
        return exceptionCode.getMessage();
    }

    public HttpStatusCode getHttpStatusCode(){
        return exceptionCode.getHttpStatus();
    }

    public String getClientExceptionCodeName(){
        return exceptionCode.getClientExceptionCode().name();
    }
}
