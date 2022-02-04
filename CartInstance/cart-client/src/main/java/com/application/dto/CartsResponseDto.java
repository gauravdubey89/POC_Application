package com.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class CartsResponseDto {
    private List<CartRequestDto> cartRequestDto;
}
