package com.thanhcong.shop.Service;

import com.thanhcong.shop.info.OrderInfo;
import com.thanhcong.shop.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Orders> findAll(Pageable pageable);
//    Orders create(OrderInfo orders);
//    Integer findMaxOrderNum();
}
