package com.cl3880.finprodpref.service;

import com.cl3880.finprodpref.dto.LikedProductRequest;
import com.cl3880.finprodpref.dto.LikedProductResponse;

import java.util.List;

/**
 * Service interface for liked product operations.
 */
public interface LikedProductService {

    /**
     * Adds a product to a user's like list.
     *
     * @param request The request containing the product details to add.
     */
    void addLikedProduct(LikedProductRequest request);

    /**
     * Retrieves the like list for a specific user.
     *
     * @param userId The ID of the user to retrieve the like list for.
     *
     * @return A list of {@link LikedProductResponse} objects.
     */
    List<LikedProductResponse> getLikedProducts(String userId);

    /**
     * Deletes a product from a user's like list.
     */
    void deleteLikedProduct(String userId, String productNo, String account);

    /**
     * Updates an existing liked product.
     *
     * @param request The request containing the product details to update.
     */
    void updateLikedProduct(LikedProductRequest request);
} 