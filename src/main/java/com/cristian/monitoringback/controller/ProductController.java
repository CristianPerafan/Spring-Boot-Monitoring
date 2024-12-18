package com.cristian.monitoringback.controller;

import com.cristian.monitoringback.api.ProductAPI;
import com.cristian.monitoringback.dto.ProductDTO;
import com.cristian.monitoringback.model.Product;
import com.cristian.monitoringback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController implements ProductAPI {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public Product getProductByID(Long id) {
        return productService.getProductByID(id);
    }

    @Override
    public Product createProduct(ProductDTO product) { return productService.createProduct(product);}

    @Override
    public Product updateProduct(Long id, Product product) {
        return productService.updateProduct(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}
