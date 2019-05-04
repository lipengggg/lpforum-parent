package com.lpforum.lpforumprovideruser.rest;

import com.lpforum.common.annotation.log.SystemLog;
import com.lpforum.lpforumprovideruser.entity.User;
import com.lpforum.lpforumprovideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    @SystemLog("获取用户列表")
    public List<User> getUser(){
        return userService.getList();
    }
}
