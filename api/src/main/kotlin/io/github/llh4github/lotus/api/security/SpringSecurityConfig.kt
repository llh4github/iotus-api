package io.github.llh4github.lotus.api.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

/**
 *
 *
 * Created At 2024/1/11 16:38
 * @author llh
 */
@EnableWebSecurity
@Configuration
class SpringSecurityConfig(
    val providers: List<AuthenticationProvider>,

) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
//            .cors{Customizer.withDefaults(it) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .logout { }
        return http.build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(providers)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}