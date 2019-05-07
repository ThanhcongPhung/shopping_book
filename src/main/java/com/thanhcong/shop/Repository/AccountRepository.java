package com.thanhcong.shop.Repository;

import com.thanhcong.shop.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {
    Account findByUserName(String username);
}
