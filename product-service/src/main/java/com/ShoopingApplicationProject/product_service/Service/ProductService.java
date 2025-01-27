package com.ShoopingApplicationProject.product_service.Service;

import com.ShoopingApplicationProject.product_service.Entity.Product;
import com.ShoopingApplicationProject.product_service.Model.ProductDto;
import com.ShoopingApplicationProject.product_service.Model.ProductDtoResponse;
import com.ShoopingApplicationProject.product_service.Repository.ImageRepository;
import com.ShoopingApplicationProject.product_service.Repository.ProductRepository;
import com.ShoopingApplicationProject.product_service.Utilities.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Queue;

@Service
@Slf4j
public class ProductService implements ProductServiceImpl{

    private final ProductRepository productRepository;

    private final ImageService imageService;


    private final ImageRepository imageRepository;


    public ProductService(ProductRepository productRepository, ImageService imageService, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }


    @Override
    public String addSingleJsonProductDto(ProductDto productDto, MultipartFile productImageFile) {

        log.info("Incoming ProductDto : {}", productDto.toString());
        //find if that category is present then fetch the last product code from the DB
        String byCategory = productRepository.findLastProductCodeByCategory(productDto.category().toString());

        //Sending the productCode || null To the Generator
        String productCode = Utils.productCodeGenerator(byCategory, productDto.category());

        String imageUrl = imageService.uploadProductImageAndReturningTheUrl(productImageFile);
        String originalFilename = productImageFile.getOriginalFilename();

        Product product = Utils.convertToProduct(productDto, productCode, imageUrl, originalFilename);

        productRepository.save(product);
        return "Success_________________________________";
    }

    @Override
    public List<ProductDtoResponse> fetchAllTheProductFromDB() {

        List<Product> all = productRepository.findAll();

        return all.stream().map(Utils::convertToDtoResponse).toList();
    }
}
