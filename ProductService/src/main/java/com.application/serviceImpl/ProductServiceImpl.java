package com.application.serviceImpl;

import com.application.dto.ProductResponseDto;
import com.application.entity.Product;
import com.application.enumerator.ErrorCode;
import com.application.exception.APIResponseException;
import com.application.repository.ProductRepository;
import com.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductResponseDto productDto) throws APIResponseException {
        return buildProductDto(productRepository.save(buildProduct(productDto)));
    }

    @Override
    public List<ProductResponseDto> getAllProduct() throws APIResponseException {
        return buildProductDtos(productRepository.findAll());
    }

    @Override
    public ProductResponseDto getById(String id) throws APIResponseException {
        return buildProductDto(productRepository.findById(id).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_100)));
    }

    private List<ProductResponseDto> buildProductDtos(List<Product> products) {
        return products.stream().map(product -> buildProductDto(product)).collect(Collectors.toList());
    }

    private ProductResponseDto buildProductDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productRate(product.getProductRate())
                .productType(product.getProductType())
                .build();
    }

    private Product buildProduct(ProductResponseDto productDto) throws APIResponseException {
        return Product.builder()
                .id(Optional.ofNullable(productDto.getId()).orElse(UUID.randomUUID().toString()))
                .productName(Optional.ofNullable(productDto.getProductName()).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_100)))
                .productRate(productDto.getProductRate())
                .productType(productDto.getProductType())
                .build();
    }
}
