package com.cristian.monitoringback.service;

import com.cristian.monitoringback.dto.ProductDTO;
import com.cristian.monitoringback.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductByID(Long id);
    Product createProduct(ProductDTO product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}