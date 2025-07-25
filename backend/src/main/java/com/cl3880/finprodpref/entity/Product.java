package com.cl3880.finprodpref.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Product entity representing products table
 */
public class Product {
    
    @NotBlank(message = "Product number is required")
    private String no;
    
    @NotBlank(message = "Product name is required")
    private String productName;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;
    
    @NotNull(message = "Fee rate is required")
    @DecimalMin(value = "0.0", message = "Fee rate must be >= 0")
    private BigDecimal feeRate;
    
    public Product() {}
    
    public Product(String no, String productName, BigDecimal price, BigDecimal feeRate) {
        this.no = no;
        this.productName = productName;
        this.price = price;
        this.feeRate = feeRate;
    }
    
    // Getters and Setters
    public String getNo() {
        return no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getFeeRate() {
        return feeRate;
    }
    
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
    
    /**
     * Get fee rate as percentage
     */
    public BigDecimal getFeeRatePercent() {
        return feeRate.multiply(BigDecimal.valueOf(100));
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "no='" + no + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", feeRate=" + feeRate +
                '}';
    }
} 