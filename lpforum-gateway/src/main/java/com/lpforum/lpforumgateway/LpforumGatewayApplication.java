package com.lpforum.lpforumgateway;

import com.lpforum.lpforumgateway.filter.filterfactory.RequestTimeGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LpforumGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LpforumGatewayApplication.class, args);
	}

	@Bean
	public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
		return new RequestTimeGatewayFilterFactory();
	}
}
