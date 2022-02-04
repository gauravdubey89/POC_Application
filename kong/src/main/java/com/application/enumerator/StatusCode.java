package com.application.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    SUCCESS("SUCCESS", 200),
    ERROR("ERROR", 0),
    EXCEPTION("EXCEPTION", 0);

    private String status;
    private int code;


}
