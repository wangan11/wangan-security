package com.springsecurity.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {


    @RequestMapping(value ="/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello word";
    }
}
