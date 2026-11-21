package com.sp.accounts;

import com.sp.accounts.dto.AccountContactInfoDTO;
import com.sp.accounts.services.clients.CardsClient;
import com.sp.accounts.services.clients.LoansClient;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDTO.class})
@ImportHttpServices(group = "loans-group", types = LoansClient.class)
@ImportHttpServices(group = "cards-group", types = CardsClient.class)
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
