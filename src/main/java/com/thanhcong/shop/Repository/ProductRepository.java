package com.thanhcong.shop.Repository;

import com.thanhcong.shop.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products,Integer> {
    @Query("select p from Products p order by p.createDate desc")
    Page<Products> findAll(Pageable pageable);

    Products findByCode(String code);
}
