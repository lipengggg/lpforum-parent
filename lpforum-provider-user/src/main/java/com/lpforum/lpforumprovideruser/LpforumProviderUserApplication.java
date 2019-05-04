package com.lpforum.lpforumprovideruser;

import com.lpforum.common.aop.SystemLogAop;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.lpforum.lpforumprovideruser",scanBasePackageClasses = SystemLogAop.class)
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(basePackages = "com.lpforum.lpforumprovideruser.dao")
public class LpforumProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LpforumProviderUserApplication.class, args);
	}

}
