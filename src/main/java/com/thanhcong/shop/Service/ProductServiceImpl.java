package com.thanhcong.shop.Service;

import com.thanhcong.shop.Repository.ProductRepository;
import com.thanhcong.shop.form.ProductForm;
import com.thanhcong.shop.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Products> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Products findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public void update(ProductForm productForm) throws Exception {
        productRepository.save(productForm.toProduct());
    }

//    @Override
//    public void save(Products products) {
//
//    }

//    @Override
//    public void save(Products products) {
//        productRepository.save(products);
//    }
}
