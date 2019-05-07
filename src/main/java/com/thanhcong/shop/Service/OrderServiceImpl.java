package com.thanhcong.shop.Service;

import com.thanhcong.shop.Repository.OrderRepository;
import com.thanhcong.shop.info.OrderInfo;
import com.thanhcong.shop.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

//    @Override
//    public Orders create(OrderInfo orders) {
//        return orderRepository.save(orders.toOrder());
//    }

//    @Override
//    public Integer findMaxOrderNum() {
//        return orderRepository.findMaxOrderNum();
//    }


}
