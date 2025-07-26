package com.cl3880.finprodpref.service;

import java.util.List;

public interface UserService {

    /**
     * Retrieves a list of user's acccounts.
     *
     * @return A list of Strings.
     */
    List<String> getUserAccounts(String userId);
} 