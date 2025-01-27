package com.ShoopingApplicationProject.product_service.Controller;


import com.ShoopingApplicationProject.product_service.Entity.Product;
import com.ShoopingApplicationProject.product_service.Model.ProductDto;
import com.ShoopingApplicationProject.product_service.Model.ProductDtoResponse;
import com.ShoopingApplicationProject.product_service.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomePage() {
        return ResponseEntity.ok("Welcome,To Product Service");
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addSingleJsonProductDto(
            @RequestParam("productDto") String productDtoJson,
            @RequestParam("productImageFile") MultipartFile productImageFile) throws IOException {

        // Manually deserialize the JSON string into ProductDto
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);


        // Now, you can use the parsed ProductDto and the image file
        String response = productService.addSingleJsonProductDto(productDto, productImageFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductDtoResponse>>fetchAllTheProductFromDB() {

        List<ProductDtoResponse> response = productService.fetchAllTheProductFromDB();

        return ResponseEntity.ok(response);
    }
}
