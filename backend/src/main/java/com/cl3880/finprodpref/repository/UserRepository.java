package com.cl3880.finprodpref.repository;

import java.util.List;

public interface UserRepository {
    List<String> getUserAccounts(String userId);
} 