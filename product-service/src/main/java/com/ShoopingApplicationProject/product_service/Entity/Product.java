package com.ShoopingApplicationProject.product_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_info")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "product_description",nullable = false)
    private String description;

    @Column(name = "product_price",nullable = false)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private ProductCategory productCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;
}
