package com.application.service;

import com.application.dto.PurchaseRequestDto;
import com.application.exception.APIResponseException;

public interface PurchaseService {
    public PurchaseRequestDto createPurchase(PurchaseRequestDto purchaseRequestDto) throws APIResponseException;

}
