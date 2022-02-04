package com.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    private String productId;
    private String productName;
    private Long productRate;
    private String productType;
}
