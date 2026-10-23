package com.sp.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account microservice Rest API Document",
                description = "DocumentAccount microservice Rest API Documentt",
                version = "v1",
                contact = @Contact(
                        name = "suraj yadav",
                        url = "http://localhost:8080/swagger-ui/index.html",
                        email = "surajyadav@gmail.com"
                ),
                license = @License(
                        name = "Apache2.0",
                        url = "https://springdoc.orgt"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Account microservice Rest API Document",
                url = "http://localhost:8080/swagger-ui/index.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
