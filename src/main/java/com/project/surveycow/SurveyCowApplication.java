package com.project.surveycow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SurveyCowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyCowApplication.class, args);
	}

}
