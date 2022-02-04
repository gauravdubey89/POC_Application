package com.application.serviceImpl;

import com.application.dto.APIResponseDto;
import com.application.dto.ProductResponseDto;
import com.application.dto.PurchaseRequestDto;
import com.application.entity.Product;
import com.application.entity.Purchase;
import com.application.enumerator.ErrorCode;
import com.application.exception.APIResponseException;
import com.application.repository.PurchaseRepository;
import com.application.service.ProductService;
import com.application.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductService productService;

    @Override
    public PurchaseRequestDto createPurchase(PurchaseRequestDto purchaseRequestDto) throws APIResponseException {
        return buildPurchaseDto(purchaseRepository.save(buildPurchase(purchaseRequestDto)));
    }

    private PurchaseRequestDto buildPurchaseDto(Purchase purchase) throws APIResponseException {
        return PurchaseRequestDto.builder()
                .id(purchase.getId())
                .productResponseDto(Optional.ofNullable(productService.getById(purchase.getProductId())).orElseThrow(() -> new APIResponseException(ErrorCode.PURC_100)))
                .build();
    }

    private Purchase buildPurchase(PurchaseRequestDto purchaseRequestDto) throws APIResponseException {
        return Purchase.builder()
                .id(Optional.ofNullable(purchaseRequestDto.getId()).orElse(UUID.randomUUID().toString()))
                .productId(Optional.ofNullable(productService.getById(purchaseRequestDto.getProductId()).getId()).orElseThrow(() -> new APIResponseException(ErrorCode.PURC_100)))
                .build();
    }
}
