#Gateway
##概述
gateway作为网关主要作用用于路由分发控制、功能过滤器（日志、）、服务熔断和流量限制等
##路由分发（断言）
###AfterRoutePredicateFactory(时间分发)
```
server:
  port: 8081
spring:
  profiles:
    active: after_route

---
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: http://httpbin.org:80/get
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
  profiles: after_route
```
###Header Route Predicate Factory（请求头分发）
```
spring:
  profiles:
    active: header_route

---
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: http://httpbin.org:80/get
        predicates:
        - Header=X-Request-Id, \d+
  profiles: header_route
```
``
请求测试：$ curl -H 'X-Request-Id:1' localhost:8081
``
###Cookie Route Predicate Factory（cookie分发）
```
spring:
  profiles:
    active: cookie_route


---
spring:
  cloud:
    gateway:
      routes:
      - id: cookie_route
        uri: http://httpbin.org:80/get
        predicates:
        - Cookie=name, forezp
  profiles: cookie_route
```
``
请求测试：$ curl -H 'Cookie:name=forezp' localhost:8081
``
###Host Route Predicate Factory(域名分发)
```
spring:
  profiles:
    active: host_route
---
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: http://httpbin.org:80/get
        predicates:
        - Host=**.fangzhipeng.com
  profiles: host_route
```
``
请求测试：curl -H 'Host:www.fangzhipeng.com' localhost:8081
``
###Method Route Predicate Factory(方式分发)
```
spring:
  profiles:
    active: method_route

---
spring:
  cloud:
    gateway:
      routes:
      - id: method_route
        uri: http://httpbin.org:80/get
        predicates:
        - Method=GET
  profiles: method_route
```
``
请求测试：$ curl -XPOST localhost:8081
``
###Path Route Predicate Factory(路径分发)
```
spring:
  profiles:
    active: path_route
---
spring:
  cloud:
    gateway:
      routes:
      - id: path_route
        uri: http://httpbin.org:80/get
        predicates:
        - Path=/foo/{segment}
  profiles: path_route
```
``
请求测试：$ curl localhost:8081/foo/dew
``
###Query Route Predicate Factory(参数分发)
```
spring:
  profiles:
    active: query_route
---
spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: http://httpbin.org:80/get
        predicates:
        - Query=foo, ba.
  profiles: query_route
  ```
  ``
  请求测试：$ curl localhost:8081?foo=bar
  ``
##过滤器
###AddRequestHeader GatewayFilter Factory(请求头过滤器)
```
server:
  port: 8081
spring:
  profiles:
    active: add_request_header_route

---
spring:
  cloud:
    gateway:
      routes:
      - id: add_request_header_route
        uri: http://httpbin.org:80/get
        filters:
        - AddRequestHeader=X-Request-Foo, Bar
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
  profiles: add_request_header_route
```
###RewritePath GatewayFilter Factory(重写路径过滤器)
```
spring:
  profiles:
    active: rewritepath_route
---
spring:
  cloud:
    gateway:
      routes:
      - id: rewritepath_route
        uri: https://blog.csdn.net
        predicates:
        - Path=/foo/**
        filters:
        - RewritePath=/foo/(?<segment>.*), /$\{segment}
  profiles: rewritepath_route
```
###自定义过滤器
```
public class RequestTimeFilter implements GatewayFilter, Ordered {

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
```
引用自定义过滤器
```
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("http://httpbin.org:80/get")
                        .order(0)
                        .id("customer_filter_router")
                )
                .build();
        // @formatter:on
    }
```
###自定义过滤器工厂
```
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {


    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    private static final String KEY = "withParams";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append("ms");
                            if (config.isWithParams()) {
                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            }
                            log.info(sb.toString());
                        }
                    })
            );
        };
    }


    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }

    }
}
```
注入过滤器工厂
```
    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }
```
配置文件
```
spring:
  profiles:
    active: elapse_route

---
spring:
  cloud:
    gateway:
      routes:
      - id: elapse_route
        uri: http://httpbin.org:80/get
        filters:
        - RequestTime=false
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
  profiles: elapse_route
```
###全局过滤器
    1、GatewayFilter : 需要通过spring.cloud.routes.filters 配置在具体路由下，只作用在当前路由上或通过spring.cloud.default-filters配置在全局，作用在所有路由上
    2、GlobalFilter : 全局过滤器，不需要在配置文件中配置，作用在所有的路由上，最终通过GatewayFilterAdapter包装成GatewayFilterChain可识别的过滤器，它为请求业务以及路由的URI转换为真实业务服务的请求地址的核心过滤器，不需要配置，系统初始化时加载，并作用在每个路由上。
```
public class TokenFilter implements GlobalFilter, Ordered {

    Logger logger=LoggerFactory.getLogger( TokenFilter.class );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            logger.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
```
注入过滤器工厂
```
@Bean
public TokenFilter tokenFilter(){
        return new TokenFilter();
}
```
##限流
###限流算法
####计数器算法
    例如：定义1S限定100个请求，没接受一个请求统计加1，到达100就清0，其他请求拒绝，
    实现：通过AtomicLong#incrementAndGet()方法来给计数器加1并返回最新值
####漏桶算法
    例如：桶内有小孔，流量是固定的，请求过来，如果流量过大，直到桶满未知，其他请求拒绝
    实现：在算法实现方面，可以准备一个队列，用来保存请求，另外通过一个线程池（ScheduledExecutorService）来定期从队列中获取请求并执行，可以一次性获取多个并发执行。
    缺点：如果遇到流量不稳定，突然大量进入请求，会出现过多请求被拒绝
####令牌桶算法
    例如：往桶内按照速率放入令牌，请求获取令牌执行请求，如果获取不到则等待，如果过剩，则弃掉多余令牌。
###限流实现
```
server:
  port: 8081
spring:
  cloud:
    gateway:
      routes:
      - id: limit_route
        uri: http://httpbin.org:80/get
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
        filters:
        - name: RequestRateLimiter
          args:
            key-resolver: '#{@hostAddrKeyResolver}'     #用于限流的键的解析器的 Bean 对象的名字
            redis-rate-limiter.replenishRate: 1                 #令牌桶每秒填充平均速率
            redis-rate-limiter.burstCapacity: 3                 #令牌桶总容量
  application:
    name: gateway-limiter
  redis:
    host: localhost
    port: 6379
    database: 0
```
根据hostAddress解析器限流
```
public class HostAddrKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

}

 @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }
```
根据uri解析器限流
```
public class UriKeyResolver  implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getURI().getPath());
    }

}

 @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }
```
#Eureka+Hystrix+Feign
Feign继承了Hystrix和Ribbon功能，Eureka消费客户端实现其功能如下
#Config
##获取配置映射
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
##注意事项：
1、配置客户端配置文件需要为bootstrap.yml，否则默认获取8888获取不到配置
2、配置服务路径配置问题，必须仓库URL，后仓库内部路径
3、路径配置映射
##配置刷新
让配置支持刷新要依赖监控功能
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
###再添加注解@RefreshScope
访问：http://localhost:8050/actuator/refresh
###添加配置
```

#关闭访问安全认证
management:
  security:
    enabled: false
#正常默认端点是health和info，没有refresh
  endpoints:
    web:
      exposure:
        include: health, info, refresh
```
###批量自动刷新配置




#Hystrix Dashboard
Hystrix Dashboard是作为断路器状态的一个组件，提供了数据监控和友好的图形化界面。
#Hystrix Turbine
看单个的Hystrix Dashboard的数据并没有什么多大的价值，要想看这个系统的Hystrix Dashboard数据就需要用到Hystrix Turbine。Hystrix Turbine将每个服务Hystrix Dashboard数据进行了整合。Hystrix Turbine的使用非常简单，只需要引入相应的依赖和加上注解和配置就可以了。
#zuul原理
#链路追踪
#日志收集
#整合Swagger
#zuual重试




``
关注资源：
https://blog.csdn.net/forezp/article/details/70148833
http://blog.didispace.com/spring-cloud-learning/
``