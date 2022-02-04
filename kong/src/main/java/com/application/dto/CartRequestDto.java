package com.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartRequestDto {
    private String userId;
    private List<ProductRequestDto> productRequestDto;
}
