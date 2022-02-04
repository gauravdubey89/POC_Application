package com.application.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private String cartId;
    private String userId;
    private List<Product> products;
}