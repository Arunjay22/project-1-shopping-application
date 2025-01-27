package com.ShoopingApplicationProject.product_service.Service;

import com.ShoopingApplicationProject.product_service.Model.ProductDto;
import com.ShoopingApplicationProject.product_service.Model.ProductDtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImpl {
    String addSingleJsonProductDto(ProductDto productDto , MultipartFile productImageFile);

    List<ProductDtoResponse> fetchAllTheProductFromDB();
}
