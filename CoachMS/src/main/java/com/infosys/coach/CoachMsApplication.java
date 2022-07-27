package com.infosys.coach;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
public class CoachMsApplication {
	
	@Bean
	ModelMapper modelMappe(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(CoachMsApplication.class, args);
	}

}
