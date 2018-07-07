package com.springsecurity.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.swing.plaf.synth.SynthColorChooserUI;

@RestController
public class HelloWordController {



    @PostConstruct
    public void  createAfter(){
        System.out.println("HelloWordController is create");
    }
    @RequestMapping(value ="/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello word";
    }

    @RequestMapping("getMe")
    public Object getMe(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @RequestMapping("getMe1")
    public Object getMe1(Authentication authentication){
        return authentication;
    }
    @RequestMapping("getMe2")
    public Object getMe2(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
