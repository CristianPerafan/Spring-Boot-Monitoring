package com.cristian.monitoringback.service;

import com.cristian.monitoringback.dto.ProductDTO;
import com.cristian.monitoringback.enums.SpringAppExceptionType;
import com.cristian.monitoringback.exception.SpringAppException;
import com.cristian.monitoringback.mapper.ProductMapper;
import com.cristian.monitoringback.model.Product;
import com.cristian.monitoringback.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final EntityManager em;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, EntityManager em) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.em = em;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new SpringAppException(SpringAppExceptionType.PRODUCT_NOT_FOUND);
        }
        return products;
    }

    @Override
    public Product getProductByID(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new SpringAppException(SpringAppExceptionType.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product createProduct(ProductDTO product) {
        return productRepository.save(productMapper.toEntity(product));
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new SpringAppException(SpringAppExceptionType.PRODUCT_NOT_FOUND);
        }
        product.setID(id);
        return em.merge(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new SpringAppException(SpringAppExceptionType.PRODUCT_NOT_FOUND);
        }
        productRepository.delete(existingProduct);
    }
}