package com.application.dto;

import com.application.enumerator.ErrorCode;
import lombok.Data;

@Data
public class ErrorCodeDto {

    private String errorCode;
    private String errorMessage;

    public ErrorCodeDto buildErrorCodeDto(ErrorCode errorCode){
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        return this;
    }
}
