package com.ShoopingApplicationProject.product_service.Utilities;

import com.ShoopingApplicationProject.product_service.Entity.Product;
import com.ShoopingApplicationProject.product_service.Entity.ProductCategory;
import com.ShoopingApplicationProject.product_service.Entity.ProductImage;
import com.ShoopingApplicationProject.product_service.Model.ProductDto;
import com.ShoopingApplicationProject.product_service.Model.ProductDtoResponse;
import com.ShoopingApplicationProject.product_service.Model.ProductImageDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {


     public static ProductDto convertToDto(Product product){

         return new ProductDto(
                 product.getId(), product.getCode(), product.getName(), product.getDescription()
                 , product.getPrice(),product.getProductCategory()
         );
    }

     public static Product convertToProduct(ProductDto productDto, String productCode, String imageUrl, String originalFilename) {

         ProductImage productImage = new ProductImage();
         productImage.setUrl(imageUrl);
         productImage.setName(originalFilename);

         // Create the Product object
         Product product = new Product();
         product.setCode(productCode);
         product.setName(productDto.name());
         product.setProductCategory(productDto.category());
         product.setDescription(productDto.description());
         product.setPrice(productDto.price());

         // Set the productImage in the Product object
         product.setProductImage(productImage);

         // Set the product in the ProductImage (bidirectional relationship)
         productImage.setProduct(product);

         return product;
    }


    public static String productCodeGenerator(String byCategory, ProductCategory category) {
        String productCode;

        if (byCategory == null) {

            long newCodeNumber = 1000;
             productCode = category.toString().substring(0, 3) + "-" + newCodeNumber;
            log.info("substring if block {}",productCode);

            return productCode;
        } else {

            try {
                int nextCodeNumber = Integer.parseInt(byCategory.substring(4, 8)) + 1;
                productCode = category.toString().substring(0,3) + "-" + nextCodeNumber;
                log.info("substring else block {}",productCode);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid String format : " + byCategory);
            }
        }


        return productCode;
    }

    public static ProductDtoResponse convertToDtoResponse(Product product) {

        ProductImageDto productImageDto = convertToDtoResponseForImageEntity(product.getProductImage());
        return new ProductDtoResponse(product.getId(), product.getCode()
                , product.getName(), product.getDescription()
                , product.getPrice(), product.getProductCategory()
                , productImageDto);
    }


    private static ProductImageDto convertToDtoResponseForImageEntity(ProductImage productImage) {

        return new ProductImageDto(productImage.getId(), productImage.getName(), productImage.getUrl());
    }

}
