package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.ProductRequestDto;
import com.application.dto.ProductResponseDto;
import com.application.util.APIObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductRedirectController {

    @PostMapping("/create")
    public ResponseEntity<APIResponseDto> createCart(@RequestBody ProductRequestDto productRequestDto) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "product", "product.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.postForEntity(
                "http://localhost:8084/api/product/create", productRequestDto,  APIResponseDto.class);
        return apiResponseDto;
    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponseDto> getAllCart() {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "product", "product.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.getForEntity(
                "http://localhost:8084/api/product/all",   APIResponseDto.class);
        return apiResponseDto;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<APIResponseDto> getPurchaseById(@PathVariable String productId) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "product", "product.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.getForEntity(
                "http://localhost:8084/api/product/"+productId, APIResponseDto.class);
        return apiResponseDto;
    }
}
