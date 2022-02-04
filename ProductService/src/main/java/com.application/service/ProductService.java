package com.application.service;

import com.application.dto.ProductRequestDto;
import com.application.exception.APIResponseException;

import java.util.List;

public interface ProductService {
    public ProductRequestDto createProduct(ProductRequestDto productDto) throws APIResponseException;

    public List<ProductRequestDto> getAllProduct() throws APIResponseException;

    public ProductRequestDto getById(String id) throws APIResponseException;
}
