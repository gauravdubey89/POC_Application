package com.application.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PROD_100("PROD_100", "Product not found"),
    PROD_101("PROD_101", "Product Price not found"),
    PROD_102("PROD_102", "Product Name not found"),
    PROD_103("PROD_103", "Product Type not found"),
    PURC_100("PURC_100", "Purchase Product not found");

    public String code;
    public String message;

}
