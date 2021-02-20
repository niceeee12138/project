package com.smallcompany.nice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@MapperScan("com.smallcompany.nice.dao")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableTransactionManagement
public class NiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceApplication.class, args);
	}

}
