package com.cl3880.finprodpref.repository;

import com.cl3880.finprodpref.dto.LikedProductResponse;
import java.util.List;

public interface LikedProductRepository {
    
    /**
     * Add a liked product using stored procedure
     */
    boolean addLikedProduct(String userId, String productNo, Integer quantity, String account);
    
    /**
     * Get liked products for a user using stored procedure
     */
    List<LikedProductResponse> getLikedProducts(String userId);
    
    /**
     * Delete a liked product using stored procedure
     */
    boolean deleteLikedProduct(String userId, String productNo, String account);
    
    /**
     * Update a liked product using stored procedure
     */
    boolean updateLikedProduct(String userId, String productNo, String oldAccount, String newAccount, Integer quantity);
} 