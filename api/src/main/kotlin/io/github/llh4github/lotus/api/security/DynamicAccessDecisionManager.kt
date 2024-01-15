package io.github.llh4github.lotus.api.security

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import java.util.function.Supplier

/**
 *
 *
 * Created At 2024/1/15 18:50
 * @author llh
 */
class DynamicAccessDecisionManager : AuthorizationManager<String> {
    override fun check(
        authentication: Supplier<Authentication>?,
        `object`: String?
    ): AuthorizationDecision? {
        authentication?.get()
        TODO("Not yet implemented")
    }
}