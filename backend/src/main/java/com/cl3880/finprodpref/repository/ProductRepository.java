package com.cl3880.finprodpref.repository;

import com.cl3880.finprodpref.entity.Product;
import java.util.List;

public interface ProductRepository {
    
    /**
     * Get all products from the view
     */
    List<Product> getAllProducts();
} 