package com.learning.feature;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ResponseCache;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProductByName(String name){
        List<Product> products=productService.getProductByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
