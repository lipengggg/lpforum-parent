package com.lpforum.lpforumprovideruser.service.impl;

import com.lpforum.common.annotation.log.SystemLog;
import com.lpforum.common.enums.EnumLogType;
import com.lpforum.lpforumprovideruser.dao.UserMapper;
import com.lpforum.lpforumprovideruser.entity.User;
import com.lpforum.lpforumprovideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @SystemLog(value = "业务层获取用户列表",logType = EnumLogType.SERVICE)
    public List<User> getList() {
        return userMapper.selectByExample(null);
    }
}
