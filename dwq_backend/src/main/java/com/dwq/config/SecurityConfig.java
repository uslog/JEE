package com.dwq.config;

import com.dwq.entity.RestBean;
import com.dwq.entity.dto.Account;
import com.dwq.entity.vo.response.AuthorizeVO;
import com.dwq.filter.JwtAuthorizeFilter;
import com.dwq.service.AccountService;
import com.dwq.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig {

    @Resource
    JwtUtils utils;    //导入

    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;

    @Resource
    AccountService service;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> conf     //拦截
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()   //拦截所有请求
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")      //进行登录
                        .failureHandler(this::onAuthenticationFailure)  //登陆失败
                        .successHandler(this::onAuthenticationSuccess)  //登陆成功
                )

                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")      //退出登录
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf->conf
                        .authenticationEntryPoint(this::onUnauthorized)
                        .accessDeniedHandler(this::onAccessDeny)       //权限不够
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf   //状态管理
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)    //添加拦截器
                .build();
    }




    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");    //告知前端数据为Json格式
        response.setCharacterEncoding("utf-8");     //设置字符编码格式
        User user = (User) authentication.getPrincipal();  //获取用户信息
        Account account=service.findAccountByNameOrEmail(user.getUsername());

        String token = utils.createJwt(user, account.getId(),account.getUsername()); //拿到token
        AuthorizeVO vo = account.asViewObject(AuthorizeVO.class,v->{
            v.setExpire(utils.expireTime());
            v.setToken(token);
        });


        response.getWriter().write(RestBean.success(vo).asJsonString());//封装在RestBean类中
    }


    //退出登录验证
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");    //告知前端数据为Json格式
        response.setCharacterEncoding("utf-8");     //设置字符编码格式
        PrintWriter writer = response.getWriter();
        String authorization =request.getHeader("Authorization");
        if(utils.invalidateJwt(authorization)) {
            writer.write(RestBean.success("退出登录成功").asJsonString());
            return;
        }
        writer.write(RestBean.failure(400, "退出登录失败").asJsonString());

    }

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");    //告知前端数据为Json格式
        response.setCharacterEncoding("utf-8");     //设置字符编码格式
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());//封装在RestBean类中

    }
    public void onAccessDeny(HttpServletRequest request,
                             HttpServletResponse response,
                             AccessDeniedException exception) throws IOException, ServletException {
        response.setContentType("application/json");    //告知前端数据为Json格式
        response.setCharacterEncoding("utf-8");     //设置字符编码格式
        response.getWriter().write(RestBean.forbidden(exception.getMessage()).asJsonString());//封装在RestBean类中

    }


    public void onUnauthorized(HttpServletRequest request,//未验证时
                               HttpServletResponse response,
                               AuthenticationException exception) throws IOException,ServletException {
        response.setContentType("application/json");    //告知前端数据为Json格式
        response.setCharacterEncoding("utf-8");     //设置字符编码格式
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());//封装在RestBean类中

    }
}
