package com.lpforum.lpforumgateway.rest;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author lipeng
 * @version Id: DemoController.java, v 0.1 2019/4/24 17:11 lipeng Exp $$
 */
@RestController
@RequestMapping("/gateway")
public class DemoController {

    /**
     * 服务熔断执行
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
