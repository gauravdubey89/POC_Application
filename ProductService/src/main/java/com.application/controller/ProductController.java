package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.ErrorCodeDto;
import com.application.dto.ProductResponseDto;
import com.application.enumerator.StatusCode;
import com.application.exception.APIResponseException;
import com.application.service.LogService;
import com.application.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LogService logService;

    @PostMapping("/create")
    public APIResponseDto createProduct(@RequestBody ProductResponseDto productDto) {
        ProductResponseDto result = new ProductResponseDto();
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            logService.logTemplate("Create Product Process", "INFO", this.getClass());
            log.info("Create Product Process");
            result = productService.createProduct(productDto);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                logService.logTemplate(exception.getMessage(), "ERROR", this.getClass());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                logService.logTemplate( exception.getMessage(), "EXCEPTION", this.getClass());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @GetMapping("/all")
    public APIResponseDto getAllProduct() {
        List<ProductResponseDto> result;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            logService.logTemplate("Get All Product Process", "INFO", this.getClass());
            log.info("Get All Product Process");
            result = productService.getAllProduct();
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                logService.logTemplate(exception.getMessage(), "EXCEPTION", this.getClass());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                logService.logTemplate(exception.getMessage(), "ERROR", this.getClass());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

    @GetMapping("/{productId}")
    public APIResponseDto getProductById(@PathVariable String productId) {
        ProductResponseDto result;
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            logService.logTemplate("Get Product Process By id "+productId, "INFO", this.getClass());
            log.info("Get Product Process By id : {}",productId);
            result = productService.getById(productId);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                logService.logTemplate(exception.getMessage(), "ERROR", this.getClass());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                logService.logTemplate( exception.getMessage(), "EXCEPTION", this.getClass());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }

}
