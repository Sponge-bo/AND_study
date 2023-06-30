package com.example.test4_2.entity;

import java.math.BigDecimal;

public class Product {

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(Integer imageUrlId) {
        this.imageUrlId = imageUrlId;
    }

    private Integer imageUrlId;
    private String productName;
    private String productPrice;

    @Override
    public String toString() {
        return "Product{" +
                "imageUrlId=" + imageUrlId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
