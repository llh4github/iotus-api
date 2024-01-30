package io.github.llh4github.lotus.api.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 *
 * Created At 2024/1/3 9:51
 * @author llh
 */
@Configuration
class WebBaseConfig(
    private val objectMapper: ObjectMapper
) : WebMvcConfigurer {

    @PostConstruct
    fun objectMapperConfig() {
        // 枚举字符串不匹配时，则为null
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOrigins("*")
//            .allowCredentials(true)
            .maxAge(3600)
    }
}
