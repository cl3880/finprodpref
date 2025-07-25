package com.cl3880.finprodpref.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    
    /**
     * Provides a ModelMapper bean for mapping between DTOs and entities.
     * 
     * @return A new instance of ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
} 