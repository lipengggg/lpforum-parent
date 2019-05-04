package com.lpforum.lpforumconsumeruser.rest;


import com.lpforum.lpforumconsumeruser.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lipeng
 * @version Id: DemoController.java, v 0.1 2019/4/25 13:59 lipeng Exp $$
 */
@RestController
@RequestMapping("/lpforum/consumer")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/getUser")
    public String getUser(){
        String user = demoService.getUser();
        System.out.println(user);
        return user;
    }
}
