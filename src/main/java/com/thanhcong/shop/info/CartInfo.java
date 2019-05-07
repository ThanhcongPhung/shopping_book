package com.thanhcong.shop.info;

import com.thanhcong.shop.Service.ProductService;
import com.thanhcong.shop.model.OrderDetail;
import com.thanhcong.shop.model.Products;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartInfo {
    private int orderNum;
    private CustomerInfo customerInfo;
    private final List<CartLineInfo> cartLine = new ArrayList<CartLineInfo>();

    CartInfo cartInfo = null;

    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CartLineInfo> getCartLine() {
        return this.cartLine;
    }

    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLine) {
            if (line.getProduct().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(ProductInfo product, int quantity) {
        CartLineInfo line = this.findLineByCode(product.getCode());
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setProduct(product);
            this.cartLine.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLine.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateProduct(String code, int quantity) {
        CartLineInfo line = this.findLineByCode(code);
        if (line != null) {
            if (quantity <= 0) {
                this.cartLine.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(ProductInfo product) {
        CartLineInfo line = this.findLineByCode(product.getCode());
        if (line != null) {
            this.cartLine.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLine.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLine) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLine) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLine();
            for (CartLineInfo line : lines) {
                this.updateProduct(line.getProduct().getCode(), line.getQuantity());
            }
        }
    }

}
