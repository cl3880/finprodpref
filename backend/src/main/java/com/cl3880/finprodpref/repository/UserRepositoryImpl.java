package com.cl3880.finprodpref.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getUserAccounts(String userId) {
        String sql = "SELECT account_no FROM sp_get_user_accounts(?)";
        return jdbcTemplate.queryForList(sql, String.class, userId);
    }
} 