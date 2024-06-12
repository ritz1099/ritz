package com.pharmeasy.config

import com.pharmeasy.constants.Profiles
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(Profiles.DEV, Profiles.QA, Profiles.QA2, Profiles.STAGING)
class OpenAPIConfiguration {
    @Value("\${openapi.server.url}")
    private lateinit var serverUrl: String

    @Value("\${openapi.server.description}")
    private lateinit var serverDescription: String
    @Bean
    fun defineOpenApi(): OpenAPI {
        val server = Server()
        server.url = serverUrl
        server.description = serverDescription

        val information: Info =
            Info()
                .title("Partner Master Service")
                .description("This API exposes endpoints of partner master service.")

        return OpenAPI().info(information).servers(listOf(server))
    }
}