package com.cl3880.finprodpref.repository;

import com.cl3880.finprodpref.dto.LikedProductResponse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String sql = "SELECT success FROM sp_add_liked_product(?, ?, ?, ?)";
        Boolean success = jdbcTemplate.queryForObject(sql, Boolean.class, userId, productNo, quantity, account);
        return Boolean.TRUE.equals(success);
    }
    
    @Override
    public List<LikedProductResponse> getLikedProducts(String userId) {
        String sql = "SELECT * FROM sp_get_liked_products(?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LikedProductResponse.class), userId);
    }
    
    @Override
    public boolean deleteLikedProduct(String userId, String productNo, String account) {
        String sql = "SELECT success FROM sp_delete_liked_product(?, ?, ?)";
        Boolean success = jdbcTemplate.queryForObject(sql, Boolean.class, userId, productNo, account);
        return Boolean.TRUE.equals(success);
    }
    
    @Override
    public boolean updateLikedProduct(String userId, String productNo, String oldAccount, String newAccount, Integer quantity) {
        String sql = "SELECT success FROM sp_update_liked_product(?, ?, ?, ?, ?)";
        Boolean success = jdbcTemplate.queryForObject(sql, Boolean.class, userId, productNo, oldAccount, newAccount, quantity);
        return Boolean.TRUE.equals(success);
    }
} 