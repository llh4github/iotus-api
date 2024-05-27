package io.github.llh4github.iotus.conf

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.customizers.GlobalOpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import java.util.function.Consumer


@Configuration
class Knife4jConfig(
    private val properties: AppProperties
) {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(properties.title)
                    .version(properties.version)
                    .description(properties.description)
                    .license(
                        License().name("Apache 2.0")
                    )
            ).addSecurityItem(SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
            .components(
                Components().addSecuritySchemes(
                    HttpHeaders.AUTHORIZATION, SecurityScheme()
                        .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")
                )
            )
    }

    @Bean
    fun orderGlobalOpenApiCustomizer(): GlobalOpenApiCustomizer {
        return GlobalOpenApiCustomizer { openApi: OpenAPI ->
            // 全局添加鉴权参数
            if (openApi.paths != null) {
                openApi.paths
                    .forEach { _: String?, pathItem: PathItem ->
                        // 为所有接口添加鉴权
                        pathItem.readOperations()
                            .forEach(Consumer {
                                it.addSecurityItem(
                                    SecurityRequirement()
                                        .addList(HttpHeaders.AUTHORIZATION)
                                )
                            })
                    }
            }
        }
    }
}