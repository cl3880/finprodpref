package com.cl3880.finprodpref.dto;

import java.math.BigDecimal;

public class LikedProductResponse {
    
    private String productName;
    private String account;
    private BigDecimal totalAmount;
    private BigDecimal totalFee;
    private String userEmail;
    
    public LikedProductResponse() {}
    
    public LikedProductResponse(String productName, String account, BigDecimal totalAmount, BigDecimal totalFee, String userEmail) {
        this.productName = productName;
        this.account = account;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.userEmail = userEmail;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    @Override
    public String toString() {
        return "LikedProductResponse{" +
                "productName='" + productName + '\'' +
                ", account='" + account + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalFee=" + totalFee +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
} 