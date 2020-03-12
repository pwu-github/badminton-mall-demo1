package com.badminton.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.badminton.mall.dao")
public class BadmintonMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadmintonMallApplication.class, args);
	}

}
