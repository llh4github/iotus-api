package io.github.llh4github.lotus.api.security

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

/**
 *
 *
 * Created At 2024/1/11 16:43
 * @author llh
 */
@Component
class UsernameAuthenticationProvider(

) : AuthenticationProvider {
    private val logger = KotlinLogging.logger {}
    override fun authenticate(authentication: Authentication): Authentication? {
        if (!supports(authentication::class.java)) {
            logger.error { "不支持的token类型: ${authentication::class.java} $authentication " }
            return null
        }
        val token = authentication as UsernameAuthToken
        return token
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernameAuthToken::class.java.isAssignableFrom(authentication)
    }
}