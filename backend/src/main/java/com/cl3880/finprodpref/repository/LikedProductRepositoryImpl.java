package com.cl3880.finprodpref.repository;

import com.cl3880.finprodpref.dto.LikedProductResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Liked product repository operations implemented using JdbcTemplate
 */
@Repository
public class LikedProductRepositoryImpl implements LikedProductRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public LikedProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public boolean addLikedProduct(String userId, String productNo, Integer quantity, String account) {
        String sql = "SELECT success, message FROM sp_add_liked_product(?, ?, ?, ?)";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, productNo, quantity, account);
        
        Boolean success = (Boolean) result.get("success");
        String message = (String) result.get("message");
        
        if (!Boolean.TRUE.equals(success)) {
            if (message != null && (message.contains("already liked"))) {
                throw new DataIntegrityViolationException("Product is already in liked list for the selected account.");
            }
            throw new RuntimeException(message != null ? message : "Failed to add liked product");
        }
        return true;
    }
    
    @Override
    public List<LikedProductResponse> getLikedProducts(String userId) {
        String sql = "SELECT * FROM sp_get_liked_products(?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LikedProductResponse.class), userId);
    }
    
    @Override
    public boolean deleteLikedProduct(String userId, String productNo, String account) {
        String sql = "SELECT success, message FROM sp_delete_liked_product(?, ?, ?)";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, productNo, account);
        
        Boolean success = (Boolean) result.get("success");
        String message = (String) result.get("message");
        
        if (!Boolean.TRUE.equals(success)) {
            throw new RuntimeException(message != null ? message : "Failed to delete liked product");
        }
        return true;
    }
    
    @Override
    public boolean updateLikedProduct(String userId, String productNo, String oldAccount, String newAccount, Integer quantity) {
        String sql = "SELECT success, message FROM sp_update_liked_product(?, ?, ?, ?, ?)";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId, productNo, oldAccount, newAccount, quantity);
        
        Boolean success = (Boolean) result.get("success");
        String message = (String) result.get("message");
        
        if (!Boolean.TRUE.equals(success)) {
            if (message != null && (message.contains("already exists") || message.contains("already liked"))) {
                throw new DataIntegrityViolationException("This product and account combination already exists in Like List.");
            }
            throw new RuntimeException(message != null ? message : "Failed to update liked product");
        }
        return true;
    }
} 