package com.cl3880.finprodpref.service;

import com.cl3880.finprodpref.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    /**
     * Retrieves a list of all available products.
     *
     * @return A list of {@link ProductDTO} objects.
     */
    List<ProductDTO> getAllProducts();
} 