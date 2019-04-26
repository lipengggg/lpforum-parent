package com.lpforum.lpforumprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LpforumProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LpforumProviderUserApplication.class, args);
	}

}
