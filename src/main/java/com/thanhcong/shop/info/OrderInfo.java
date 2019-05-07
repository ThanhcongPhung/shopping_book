package com.thanhcong.shop.info;

import com.thanhcong.shop.Service.OrderService;
import com.thanhcong.shop.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderInfo {
    @Autowired
    private OrderService orderService;
    CartInfo cartInfo = null;
    private String id;
    private Date orderDate;
    private int orderNum;
    private double amount;
    private String customerName;
    private String customerAddres;
    private String customerEmail;
    private String customerPhone;
    private List<OrderDetailsInfo> detail;

    public OrderInfo() {

    }

    public OrderInfo(String id, Date orderDate, int orderNum, double amount,
                     String customerName, String customerAddres, String customerEmail, String customerPhone) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.amount = amount;
        this.customerName = customerName;
        this.customerAddres = customerAddres;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddres() {
        return customerAddres;
    }

    public void setCustomerAddres(String customerAddres) {
        this.customerAddres = customerAddres;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<OrderDetailsInfo> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderDetailsInfo> detail) {
        this.detail = detail;
    }

//    public Orders toOrder() {
//        Orders orders = new Orders();
//        int orderNum = orderService.findMaxOrderNum() + 1;
//        orders.setId(UUID.randomUUID().toString());
//        orders.setOrderNum(orderNum);
//        orders.setOrderDate(new Date());
//        orders.setAmount(cartInfo.getAmountTotal());
//
//        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//        orders.setCustomerName(customerInfo.getName());
//        orders.setCustomerEmail(customerInfo.getEmail());
//        orders.setCustomerPhone(customerInfo.getPhone());
//        orders.setCustomerAddress(customerInfo.getAddress());
//        return orders;
//
//    }
}
