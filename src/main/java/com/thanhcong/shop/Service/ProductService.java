package com.thanhcong.shop.Service;

import com.thanhcong.shop.form.ProductForm;
import com.thanhcong.shop.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ProductService {
    Page<Products> findAll(Pageable pageable);
    Products findByCode(String code);

    void update(ProductForm productForm) throws Exception;

//    void save(ProductForm productForm);
//    void save();

}
