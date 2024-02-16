package com.plucas.ws.products.controller;

import com.plucas.ws.products.dto.ProductRequestDTO;
import com.plucas.ws.products.error.ErrorMessage;
import com.plucas.ws.products.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequestDTO product) {
        String productId;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            log.error("Error occurred while creating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorMessage(
                            java.time.LocalDateTime.now(),
                            e.getMessage(),
                            "products/api/create"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
