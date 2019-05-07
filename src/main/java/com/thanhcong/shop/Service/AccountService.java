package com.thanhcong.shop.Service;

import com.thanhcong.shop.model.Account;

public interface AccountService {
    Iterable<Account> findAll();
    public Account findByUserName(String username);
}
