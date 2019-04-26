#springcloud之gateway
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
