package com.micro.service.galaxycore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.micro.service.galaxycore.dao")
public class GalaxyCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyCoreApplication.class, args);
	}

}
