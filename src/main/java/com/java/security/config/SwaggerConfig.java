package com.java.security.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Deven Danidhariya
 */
@Configuration
public class SwaggerConfig {

  private static final String BEARER_AUTH = "BearerAuth";

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI().info(
            new io.swagger.v3.oas.models.info.Info().title("Security").version("0.0.1").description("")
                .contact(new io.swagger.v3.oas.models.info.Contact().name("Deven Danidhariya")
                    .email("devenxjob@gmail.com")).license(
                    new io.swagger.v3.oas.models.info.License().name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
        .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH)).components(
            new Components().addSecuritySchemes(BEARER_AUTH,
                new SecurityScheme().name(BEARER_AUTH).type(SecurityScheme.Type.HTTP)
                    .scheme("bearer").bearerFormat("JWT")));
  }

}
