package com.thanhcong.shop.Repository;

import com.thanhcong.shop.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Orders, Integer> {
    Page<Orders> findAll(Pageable pageable);

//    @Query("select max(orders.orderNum) from orders ")
//    Integer findMaxOrderNum();
}
