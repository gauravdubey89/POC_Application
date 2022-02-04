package com.application.dto;

import lombok.Data;

@Data
public class APIResponseDto {
    private String status;
    private Object data;
    private ErrorCodeDto errorCodeDto;
    private int statusCode;

    public APIResponseDto(String status, int statusCode, Object data) {
        this.status = status;
        this.data = data;
        this.statusCode = statusCode;
    }

    public APIResponseDto(String status, ErrorCodeDto errorCodeDto) {
        this.status = status;
        this.errorCodeDto = errorCodeDto;
    }
}
