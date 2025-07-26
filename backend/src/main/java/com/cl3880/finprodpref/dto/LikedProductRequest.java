package com.cl3880.finprodpref.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LikedProductRequest {

    @NotBlank(message = "User ID is required")
    private String userId;
    
    @NotBlank(message = "Product number is required")
    private String productNo;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
    
    private String newAccount; 
    private String oldAccount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

    public String getOldAccount() {
        return oldAccount;
    }

    public void setOldAccount(String oldAccount) {
        this.oldAccount = oldAccount;
    }

    @Override
    public String toString() {
        return "LikedProductRequest{" +
                "userId='" + userId + '\'' +
                ", productNo='" + productNo + '\'' +
                ", quantity=" + quantity +
                ", newAccount='" + newAccount + '\'' +
                ", oldAccount='" + oldAccount + '\'' +
                '}';
    }
} 