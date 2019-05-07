package com.thanhcong.shop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;
    @Basic
    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "QUANITY", nullable = false)
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
    private Products product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount(double amount) {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuanity(int quantity) {
        return quantity;
    }

    public void setQuanity(int quanity) {
        this.quantity = quanity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}

