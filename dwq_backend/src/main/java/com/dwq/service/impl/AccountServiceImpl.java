package com.dwq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwq.entity.dto.Account;
import com.dwq.mapper.AccountMapper;
import com.dwq.service.AccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account= this.findAccountByNameOrEmail(username);
        if (account==null)
            throw new UsernameNotFoundException("用户名或密码错误");

        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public Account findAccountByNameOrEmail(String test) {
        return this.query()
                .eq("username",test).or()
                .eq("email",test)
                .one();
    }
}
