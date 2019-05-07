package com.thanhcong.shop.Service;

import com.thanhcong.shop.Repository.AccountRepository;
import com.thanhcong.shop.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account accounts = accountService.findByUserName(userName);
        System.out.println("Account=" + accounts);
        if (accounts == null) {
            throw new UsernameNotFoundException("User " + userName + "was not found in the database");
        }
        String role = accounts.getUserRole();
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        return new User(accounts.getUserName(), accounts.getEncrytedPassword(), grantList);


    }
}
