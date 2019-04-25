package com.lpforum.lpforumgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 *日志过滤器
 * @author lipeng
 * @version Id: LogFilter.java, v 0.1 2019/4/25 10:27 lipeng Exp $$
 */
public class LogFilter implements GatewayFilter,Ordered{

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public ShortcutType shortcutType() {
        return null;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return null;
    }

    @Override
    public String shortcutFieldPrefix() {
        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }
}
