package com.application.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "purchase")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "productId", nullable = false)
    private String productId;

    @CreatedDate
    private Date createdDate;

}