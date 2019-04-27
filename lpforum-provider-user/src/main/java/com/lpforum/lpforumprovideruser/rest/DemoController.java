package com.lpforum.lpforumprovideruser.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(){
        return "你好";
    }
}
