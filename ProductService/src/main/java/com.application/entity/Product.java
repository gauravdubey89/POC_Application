package com.application.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "product")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_rate", nullable = false)
    private Long productRate;

    @Column(name = "product_type", length = 45)
    private String productType;

}