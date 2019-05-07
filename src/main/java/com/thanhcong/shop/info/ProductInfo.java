package com.thanhcong.shop.info;

import com.thanhcong.shop.model.Products;

public class ProductInfo {
    private String code;
    private String name;
    private double price;

    public ProductInfo(String code, String name, double price) {

    }

    public ProductInfo(Products products) {
        this.code = products.getCode();
        this.name = products.getName();
        this.price = products.getPrice();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
