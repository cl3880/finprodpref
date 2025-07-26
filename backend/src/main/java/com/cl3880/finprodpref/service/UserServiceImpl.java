package com.cl3880.finprodpref.service;

import com.cl3880.finprodpref.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getUserAccounts(String userId) {
        return userRepository.getUserAccounts(userId);
    }
} 