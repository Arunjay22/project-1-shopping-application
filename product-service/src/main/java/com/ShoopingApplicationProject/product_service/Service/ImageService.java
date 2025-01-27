package com.ShoopingApplicationProject.product_service.Service;

import com.ShoopingApplicationProject.product_service.Entity.Product;
import com.ShoopingApplicationProject.product_service.Entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class ImageService {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    public String uploadProductImageAndReturningTheUrl(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        try{

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(originalFilename)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            String imageUrl = String.format("http://%s.s3.amazonaws.com/%s", bucketName, originalFilename);

            return imageUrl;

        } catch (IOException e) {
            throw new RuntimeException("Something Went Wrong While Uploading Image");
        }

    }
}
