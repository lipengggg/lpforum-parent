package com.lpforum.lpforumgateway.hystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author lipeng
 * @version Id: FallBackHystrix.java, v 0.1 2019/4/26 15:33 lipeng Exp $$
 */
public class FallBackHystrix {

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

}
