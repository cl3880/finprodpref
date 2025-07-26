package com.cl3880.finprodpref.dto;

import java.math.BigDecimal;

public class LikedProductResponse {
    
    private String productNo;
    private String productName;
    private String account;
    private Integer orderName;
    private BigDecimal totalAmount;
    private BigDecimal totalFee;
    private String userEmail;
    
    public LikedProductResponse() {}
    
    public LikedProductResponse(String productNo, String productName, String account, Integer orderName, BigDecimal totalAmount, BigDecimal totalFee, String userEmail) {
        this.productNo = productNo;
        this.productName = productName;
        this.account = account;
        this.orderName = orderName;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.userEmail = userEmail;
    }
    
    public String getProductNo() {
        return productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
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
    
    public Integer getOrderName() {
        return orderName;
    }
    
    public void setOrderName(Integer orderName) {
        this.orderName = orderName;
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
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", account='" + account + '\'' +
                ", orderName=" + orderName +
                ", totalAmount=" + totalAmount +
                ", totalFee=" + totalFee +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
} 