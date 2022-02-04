package com.application.controller;

import com.application.dto.APIResponseDto;
import com.application.dto.ErrorCodeDto;
import com.application.dto.PurchaseRequestDto;
import com.application.enumerator.StatusCode;
import com.application.exception.APIResponseException;
import com.application.service.LogService;
import com.application.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private LogService logService;

    @PostMapping("/create")
    public APIResponseDto createPurchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        PurchaseRequestDto result = new PurchaseRequestDto();
        ErrorCodeDto errorCodeDto = new ErrorCodeDto();
        try {
            logService.logTemplate("Create Purchase Process", "INFO", this.getClass());
            log.info("Create Product Process");
            result = purchaseService.createPurchase(purchaseRequestDto);
            return new APIResponseDto(StatusCode.SUCCESS.getStatus(), StatusCode.SUCCESS.getCode(), result);
        } catch (Exception exception) {
            if (exception instanceof APIResponseException) {
                log.error("Error : {}", exception.getMessage());
                logService.logTemplate( exception.getMessage(), "ERROR", this.getClass());
                return new APIResponseDto(StatusCode.ERROR.getStatus(), errorCodeDto.buildErrorCodeDto(((APIResponseException) exception).getErrorCode()));
            } else {
                log.error("Exception : {}", exception.getMessage());
                logService.logTemplate(exception.getMessage(), "EXCEPTION", this.getClass());
                return new APIResponseDto(StatusCode.EXCEPTION.getStatus(), StatusCode.EXCEPTION.getCode(), exception);
            }
        }
    }
}
