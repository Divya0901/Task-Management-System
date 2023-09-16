package com.dg.Task_Management_System;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot application for Task Management",
				version="1.0.0",
				description = "A Spring boot web application where the Restful APIS are developed to perform all the" +
						" CURD operations using Htpp methods to manage the tasks in an organized way.",
				termsOfService = "Divya Gujjuka",
				contact = @Contact(
						name = "Divya Gujjuka",
						email = "gujjukadivya0109@gmail.com"
				),
				license = @License(
						name = "licence",
						url = "Divya Gujjuka"
				)
		)
)
public class TaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

}
