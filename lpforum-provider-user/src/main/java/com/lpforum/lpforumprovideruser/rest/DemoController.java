package com.lpforum.lpforumprovideruser.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {

    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(){
        return initialSize+"";
    }
}
