package com.dwq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dwq.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String test);

}