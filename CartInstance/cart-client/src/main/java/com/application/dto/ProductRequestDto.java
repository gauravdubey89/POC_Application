package com.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductRequestDto implements Serializable {
    private String productId;
    private String productName;
    private Long productRate;
    private String productType;
}
