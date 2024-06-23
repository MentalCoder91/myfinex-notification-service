package com.expleo.notification_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Notification microservice Async Documentation",
				description = "MyFinEx Notification microservice Async Spring cloud Stream Consumer",
				version = "v1",
				contact = @Contact(
						name = "Anish Alwekar",
						email = "anish.alwekar@gmail.com",
						url = "https://github.com/MentalCoder91"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/MentalCoder91"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "MyFinEx Notification microservice uses Rabbit MQ Binder with Spring cloud stream",
				url = "http://localhost:9096/swagger-ui/index.html"
		)
)
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
