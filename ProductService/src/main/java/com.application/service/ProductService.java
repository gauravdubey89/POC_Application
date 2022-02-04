package com.application.service;

import com.application.dto.ProductResponseDto;
import com.application.exception.APIResponseException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto createProduct(ProductResponseDto productDto) throws APIResponseException;

    public List<ProductResponseDto> getAllProduct() throws APIResponseException;

    public ProductResponseDto getById(String id) throws APIResponseException;
}
