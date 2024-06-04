package com.riwi.prueba_desempeno.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuracion de swagger
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Api for admin surveys and questions", version = "1.0", description = "Surveys management documetation api"))
public class OpenApiConfig {

}