package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {})
public class SpringSecurityDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemo.class,args);
    }

}
