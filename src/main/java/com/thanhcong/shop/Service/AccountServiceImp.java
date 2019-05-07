package com.thanhcong.shop.Service;

import com.thanhcong.shop.Repository.AccountRepository;
import com.thanhcong.shop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByUserName(String username) {
        return accountRepository.findByUserName(username);
    }
}
