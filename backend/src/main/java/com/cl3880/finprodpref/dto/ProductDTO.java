package com.cl3880.finprodpref.dto;

import java.math.BigDecimal;

public class ProductDTO {
    
    private String productNo;
    private String productName;
    private BigDecimal price;
    private BigDecimal feeRate;

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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", feeRate=" + feeRate +
                '}';
    }
} 