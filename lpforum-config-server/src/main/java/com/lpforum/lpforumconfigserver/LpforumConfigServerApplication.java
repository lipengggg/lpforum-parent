package com.lpforum.lpforumconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class LpforumConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LpforumConfigServerApplication.class, args);
	}

}
