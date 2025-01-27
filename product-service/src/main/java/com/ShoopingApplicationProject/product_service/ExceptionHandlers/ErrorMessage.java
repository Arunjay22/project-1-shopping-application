package com.ShoopingApplicationProject.product_service.ExceptionHandlers;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessage {

    private String message;

    private HttpStatus httpStatus;


}
