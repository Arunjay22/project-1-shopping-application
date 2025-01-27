package com.ShoopingApplicationProject.product_service.Repository;

import com.ShoopingApplicationProject.product_service.Entity.Product;
import com.ShoopingApplicationProject.product_service.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "SELECT product_code FROM product_info WHERE product_category = :category ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastProductCodeByCategory(@Param("category") String category);
}
