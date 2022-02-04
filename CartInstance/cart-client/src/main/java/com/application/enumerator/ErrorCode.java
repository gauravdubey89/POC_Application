package com.application.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PROD_100("PROD_100", "Product not found");

    public String code;
    public String message;

}
