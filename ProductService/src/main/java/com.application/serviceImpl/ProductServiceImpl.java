package com.application.serviceImpl;

import com.application.dto.ProductRequestDto;
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
    public ProductRequestDto createProduct(ProductRequestDto productDto) throws APIResponseException {
        return buildProductDto(productRepository.save(buildProduct(productDto)));
    }

    @Override
    public List<ProductRequestDto> getAllProduct() throws APIResponseException {
        return buildProductDtos(productRepository.findAll());
    }

    @Override
    public ProductRequestDto getById(String id) throws APIResponseException {
        return buildProductDto(productRepository.findById(id).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_100)));
    }

    private List<ProductRequestDto> buildProductDtos(List<Product> products) {
        return products.stream().map(product -> buildProductDto(product)).collect(Collectors.toList());
    }

    private ProductRequestDto buildProductDto(Product product) {
        return ProductRequestDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productRate(product.getProductRate())
                .productType(product.getProductType())
                .build();
    }

    private Product buildProduct(ProductRequestDto productDto) throws APIResponseException {
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .productName(Optional.ofNullable(productDto.getProductName()).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_100)))
                .productRate(Optional.ofNullable(productDto.getProductRate()).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_101)))
                .productType(Optional.ofNullable(productDto.getProductType()).orElseThrow(() -> new APIResponseException(ErrorCode.PROD_102)))
                .build();
    }
}
