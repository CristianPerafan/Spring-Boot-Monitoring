package com.cristian.monitoringback.api;

import com.cristian.monitoringback.dto.ProductDTO;
import com.cristian.monitoringback.model.Product;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping(ProductAPI.BASE_URL)
public interface ProductAPI {

    String BASE_URL = "/products";

    @GetMapping
    List<Product> getAllProducts();

    @GetMapping("/{id}")
    Product getProductByID(@PathVariable Long id);

    @PostMapping
    Product createProduct(@RequestBody @Valid ProductDTO product);

    @PutMapping("/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product product);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id);

}