package com.lpforum.lpforumconsumeruser.hystrix;

import com.lpforum.lpforumconsumeruser.service.DemoService;
import org.springframework.stereotype.Service;

/**
 *
 * @author lipeng
 * @version Id: DemoServiceHystric.java, v 0.1 2019/4/26 16:14 lipeng Exp $$
 */
@Service
public class DemoServiceHystric implements DemoService{

    @Override
    public String getUser() {
        return "服务超时，请重新再试";
    }
}
