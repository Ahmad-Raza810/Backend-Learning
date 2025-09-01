package com.learning.feature;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Product>> getProductByName(@RequestParam("query") String name){
        List<Product> products=productService.getProductByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("product")
    public ResponseEntity<Product>  addProduct(@Valid @RequestBody ProductDTO productDTO){
        Product product=productService.addProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
