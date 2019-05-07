package com.thanhcong.shop.Repository;

import com.thanhcong.shop.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,String> {
}
