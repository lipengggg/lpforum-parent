package com.lpforum.lpforumconsumeruser.service;

import com.lpforum.lpforumconsumeruser.hystrix.DemoServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lipeng
 * @version Id: DemoService.java, v 0.1 2019/4/26 16:04 lipeng Exp $$
 */
@FeignClient(value = "lpforum-provider-user",fallback = DemoServiceHystric.class)
public interface DemoService {
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    String getUser();
}
