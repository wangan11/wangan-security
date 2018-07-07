package com.springsecurity.browser.config;

import com.springsecurity.browser.handler.MyAuthenticationFailureHandler;
import com.springsecurity.browser.handler.MyAuthenticationSuccessHandler;
import com.springsecurity.browser.service.MyUserDetailsService;
import com.springsecurity.core.properties.SecurityProperties;
import com.springsecurity.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfige extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class).formLogin().loginPage("/brower/login")
               .successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler).loginProcessingUrl("/authention/form").
                and().authorizeRequests()
                .antMatchers("/brower/login",securityProperties.getBrowser().getLoginUrl(),"/get/imgecode").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }

    @Bean
    public MyUserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
