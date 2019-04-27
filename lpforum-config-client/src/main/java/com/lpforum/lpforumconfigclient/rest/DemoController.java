package com.lpforum.lpforumconfigclient.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {

    @Value("${foo}")
    private String foo;

    @RequestMapping(value = "/getFoo",method = RequestMethod.GET)
    public String getFoo(){
        return foo;
    }
}
