package com.application.exception;

import com.application.enumerator.ErrorCode;
import lombok.Getter;

@Getter
public class APIResponseException extends Exception{

    String code;
    ErrorCode errorCode;

    public APIResponseException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.errorCode = errorCode;
    }

    APIResponseException(String message, Throwable throwable){
        super(message, throwable);
    }
}
