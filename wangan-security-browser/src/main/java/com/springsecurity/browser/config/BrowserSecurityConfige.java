package com.springsecurity.browser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class BrowserSecurityConfige extends WebSecurityConfigurerAdapter {

  public BrowserSecurityConfige(){
      System.out.println("TestConfiguration容器启动初始化。。。");
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin().loginPage("/login").and().authorizeRequests().anyRequest().authenticated();
    }

}
