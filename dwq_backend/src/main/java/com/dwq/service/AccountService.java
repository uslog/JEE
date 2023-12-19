package com.dwq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dwq.entity.dto.Account;
import com.dwq.entity.vo.request.ConfirmResetVO;
import com.dwq.entity.vo.request.EmailRegisterVO;
import com.dwq.entity.vo.request.EmailResetVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {

    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String registerEmailAccount(EmailRegisterVO info, String role);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);

}