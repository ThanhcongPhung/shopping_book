package com.thanhcong.shop.form;

import com.thanhcong.shop.model.Products;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public class ProductForm {
    private String code;
    private String name;
    private double price;
    private boolean newProduct = false;
    //upload file
    private MultipartFile fileData;

    public ProductForm() {
        this.newProduct = true;
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

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public ProductForm(Products product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Products toProduct()throws Exception {
        Products products = new Products();
        products.setCode(this.getCode());
        products.setName(this.getName());
        products.setPrice(this.getPrice());
        products.setCreateDate(new Date());
        products.setImage(this.getFileData().getBytes());
        return products;
    }
}
