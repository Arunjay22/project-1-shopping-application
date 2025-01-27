package com.ShoopingApplicationProject.product_service.Repository;

import com.ShoopingApplicationProject.product_service.Entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ProductImage,Long> {
}
