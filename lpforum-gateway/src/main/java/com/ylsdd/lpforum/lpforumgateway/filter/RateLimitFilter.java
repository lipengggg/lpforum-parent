package com.ylsdd.lpforum.lpforumgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ShortcutConfigurable;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 *限流过滤器
 * @author lipeng
 * @version Id: RateLimitFilter.java, v 0.1 2019/4/25 10:30 lipeng Exp $$
 */
public class RateLimitFilter implements GatewayFilter,Ordered{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }

    /**
     * 过滤器执行的优先等级
     * @return
     */
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
}
