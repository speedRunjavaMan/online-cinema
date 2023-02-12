package com.SberProjectUEN.java13springTU.libraryproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    //http://localhost:9090/api/rest/swagger-ui/index.htm
    @Bean
    public OpenAPI libraryProject() {
        return new OpenAPI()
              .info(new Info()
                          .title("Онлайн кинотеатр")
                          .description("Сервис, позволяющий арендовать фильм в онлайн кинотеатре.")
                          .version("v0.1")
                          .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                          .contact(new Contact().name("Evgenii N. Uvarov")
                                         .email("simple@yandex.ru")
                                         .url(""))
                   );
    }
}
