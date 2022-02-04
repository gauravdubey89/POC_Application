package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.CartRequestDto;
import com.application.util.APIObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
public class CartRedirectController {

    @PostMapping("/create")
    public ResponseEntity<APIResponseDto> createCart(@RequestBody CartRequestDto cartRequestDto) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "cart", "cart.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.postForEntity(
                "http://localhost:8081/api/cart/create", cartRequestDto,  APIResponseDto.class);
        return apiResponseDto;
    }

    @PutMapping("/update/{id}")
    public void updateCart(@RequestBody CartRequestDto cartRequestDto, @RequestParam String id) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "cart", "cart.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        restTemplate.put("http://localhost:8081/api/cart/update/"+id, cartRequestDto);
    }

    @GetMapping("/get/{cartId}")
    public ResponseEntity<APIResponseDto> getCartById(@PathVariable String cartId) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "cart", "cart.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.getForEntity(
                "http://localhost:8081/api/cart/"+cartId,   APIResponseDto.class);
        return apiResponseDto;
    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponseDto> getAllCart() {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "cart", "cart.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        ResponseEntity<APIResponseDto> apiResponseDto = restTemplate.getForEntity(
                "http://localhost:8081/api/cart/all",   APIResponseDto.class);
        return apiResponseDto;
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable String cartId) {
        RestTemplate restTemplate = new RestTemplate();
        APIObject stockAPI = new APIObject(
                "cart", "cart.api", "http://localhost:8001", "/");
        HttpEntity<APIObject> apiEntity = new HttpEntity<>(stockAPI);
        restTemplate.delete("http://localhost:8081/api/cart/"+cartId);
    }
}
