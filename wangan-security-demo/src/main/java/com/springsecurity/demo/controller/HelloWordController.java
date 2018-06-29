package com.springsecurity.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

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
}
