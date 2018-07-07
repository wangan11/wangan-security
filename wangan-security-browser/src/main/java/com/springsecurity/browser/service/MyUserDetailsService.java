package com.springsecurity.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MyUserDetailsService  implements UserDetailsService {

    private Logger log=LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户username = "+username);
        return new User(username,bCryptPasswordEncoder.encode("123456"),
                true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
