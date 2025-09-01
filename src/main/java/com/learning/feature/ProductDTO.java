package com.learning.feature;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    @NotBlank(message = "product name is required.")
    private String name;

    @NotNull(message = "price can not be empty")
    @DecimalMin(value ="0.0",inclusive = false,message = "price should greater than 0")
    private Double price;


    public static Product dtoToProduct(ProductDTO productDTO){
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return  product;
    }
}
