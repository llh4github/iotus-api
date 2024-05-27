package io.github.llh4github.iotus.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SpringSecurityConfig(
//    val providers: List<AuthenticationProvider>,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

//    @Bean
//    fun authenticationManager(): AuthenticationManager = ProviderManager(providers)

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.authenticationEntryPoint(AuthenticationFailedHandler())
                it.accessDeniedHandler(AccessDeniedHandlerImpl())
            }
            .formLogin { it.loginProcessingUrl("/auth/login").failureHandler(AuthenticationFailureHandlerImpl()) }
            .logout { it.disable() }
            .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}