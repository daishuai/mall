package com.macro.mall.portal.config;

import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * mall-security模块相关配置
 * Created by macro on 2019/11/5.
 */
@Configuration
public class MallSecurityConfig {

    @Resource
    private UmsMemberService memberService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> memberService.loadUserByUsername(username);
    }
}
