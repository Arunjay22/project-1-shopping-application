package com.ShoopingApplicationProject.product_service.Model;

import com.ShoopingApplicationProject.product_service.Entity.ProductCategory;

import java.math.BigDecimal;

public record ProductDtoResponse(
        Long id,
        String code,
        String name,
        String description,
        BigDecimal price,
        ProductCategory category,
        ProductImageDto productImageDto
) {

}
