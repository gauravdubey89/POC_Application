package com.application.entity;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private String productId;
    private String productName;
    private Long productRate;
    private String productType;
}