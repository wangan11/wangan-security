package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpringSecurityDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemo.class,args);
    }

}
