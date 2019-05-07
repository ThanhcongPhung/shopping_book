package com.thanhcong.shop.info;

public class CartLineInfo {
    private ProductInfo product;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.product.getPrice() * this.quantity;
    }

}
