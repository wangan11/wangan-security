package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    public BrowserSecurityConfig(){
        System.out.printf("aaaa");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();

    }

}
