package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.ProductResponseDto;
import com.application.dto.PurchaseRequestDto;
import com.application.util.APIObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseRedirectController {

    @PostMapping("/create")
    public ResponseEntity<APIResponseDto> createCart(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "purchase", "purchase.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.postForEntity(
                "http://localhost:8084/api/purchase/create", purchaseRequestDto,  APIResponseDto.class);
        return apiResponseDto;
    }
}
