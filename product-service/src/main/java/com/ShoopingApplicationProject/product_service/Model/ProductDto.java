package com.ShoopingApplicationProject.product_service.Model;

import com.ShoopingApplicationProject.product_service.Entity.ProductCategory;
import com.ShoopingApplicationProject.product_service.Entity.ProductImage;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String code,
        String name,
        String description,
        BigDecimal price,
        ProductCategory category

) {

}
