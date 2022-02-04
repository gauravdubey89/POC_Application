package com.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CartRequestDto implements Serializable {

    private String cartId;
    private String userId;
    private List<ProductRequestDto> productRequestDto;
}
