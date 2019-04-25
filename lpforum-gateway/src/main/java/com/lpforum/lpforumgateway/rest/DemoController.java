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
public class DemoController {

    /**
     * 配置路由，包括熔断，HTTP请求SpringCloud gateway，
     * 如果Gateway Handler Mapping确定请求与路由匹配（请求与路由的匹配用到predicate），
     * 则将其发送到Gateway web handler处理，Gateway web handler处理请求
     * 时会经过一系列的过滤器链，可以在请求前pre，请求后post过滤，通常用来鉴权、
     * 限流、日志等
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }

    /**
     * 服务熔断执行
     * @return
     */
    @RequestMapping("/fallback")
    public Mono<String> fallback(){
        return Mono.just("fallback");
    }
}
