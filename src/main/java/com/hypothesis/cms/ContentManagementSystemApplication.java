package com.hypothesis.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "cms", version = "1.0", description = "Content Management System"))
public class ContentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentManagementSystemApplication.class, args);
	}

}
