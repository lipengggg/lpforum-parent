package com.ylsdd.lpforum.lpforumgateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author lipeng
 * @version Id: RequestFilter.java, v 0.1 2019/4/25 8:58 lipeng Exp $$
 */
public class RequestFilter implements GatewayFilter, Ordered{

        private static final Log log = LogFactory.getLog(GatewayFilter.class);
        private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                        }
                    })
            );

        }

        @Override
        public int getOrder() {
            return 0;
        }
}
