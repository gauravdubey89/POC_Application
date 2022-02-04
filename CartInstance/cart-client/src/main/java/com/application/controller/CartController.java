package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.CartRequestDto;
import com.application.dto.CartsResponseDto;
import com.application.dto.ErrorCodeDto;
import com.application.enumerator.StatusCode;
import com.application.exception.APIResponseException;
import com.application.service.CartClientService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Descriptors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/cart")
@RestController
@Slf4j
public class CartController {

    @Autowired
    CartClientService cartClientService;


    @PostMapping("/create")
    public APIResponseDto createCart(@RequestBody CartRequestDto cartRequestDto) {
        CartRequestDto result ;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            log.info("Create Cart Process");
            result = cartClientService.createCart(cartRequestDto);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @PutMapping("/update/{id}")
    public APIResponseDto updateCart(@RequestBody CartRequestDto cartRequestDto, @PathVariable String id) {
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            log.info("Create Cart Process");
            cartClientService.updateCart(cartRequestDto, id);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), null);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @GetMapping("/{cartId}")
    public APIResponseDto getCart(@PathVariable String cartId) {
        CartRequestDto result;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            log.info("Get Cart By Id Process : {}",cartId);
            result = cartClientService.getCart(cartId);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @DeleteMapping("/{cartId}")
    public APIResponseDto deleteCart(@PathVariable String cartId) {
        Map<Descriptors.FieldDescriptor, Object> result;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            log.info("Create Product Process");
            result = cartClientService.deleteCart(cartId);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @GetMapping("/all")
    public APIResponseDto getAllCart() {
        List<CartRequestDto> result;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            log.info("Create Product Process");
            result = cartClientService.getAllCart();
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }
}
