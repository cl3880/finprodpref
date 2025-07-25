package com.cl3880.finprodpref.repository;

import com.cl3880.finprodpref.entity.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Product repository operations implemented using JdbcTemplate
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM v_product_list";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }
} 