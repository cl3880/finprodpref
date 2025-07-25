package com.cl3880.finprodpref.service;

import com.cl3880.finprodpref.dto.LikedProductRequest;
import com.cl3880.finprodpref.dto.LikedProductResponse;
import com.cl3880.finprodpref.repository.LikedProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LikedProductServiceImpl implements LikedProductService {

    private final LikedProductRepository likedProductRepository;

    public LikedProductServiceImpl(LikedProductRepository likedProductRepository) {
        this.likedProductRepository = likedProductRepository;
    }

    @Transactional
    @Override
    public void addLikedProduct(LikedProductRequest request) {
        likedProductRepository.addLikedProduct(
            request.getUserId(),
            request.getProductNo(),
            request.getQuantity(),
            request.getNewAccount()
        );
    }

    @Override
    public List<LikedProductResponse> getLikedProducts(String userId) {
        return likedProductRepository.getLikedProducts(userId);
    }

    @Transactional
    @Override
    public void deleteLikedProduct(String userId, String productNo, String account) {
        likedProductRepository.deleteLikedProduct(userId, productNo, account);
    }

    @Transactional
    @Override
    public void updateLikedProduct(LikedProductRequest request) {
        likedProductRepository.updateLikedProduct(
            request.getUserId(),
            request.getProductNo(),
            request.getOldAccount(),
            request.getNewAccount(),
            request.getQuantity()
        );
    }
} 