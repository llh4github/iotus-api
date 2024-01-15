package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.model.HttpMethodEnums
import io.github.llh4github.lotus.model.convertMethodEnums
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.util.AntPathMatcher
import java.util.function.Supplier

/**
 *
 *
 * Created At 2024/1/15 18:50
 * @author llh
 */
class UrlAccessDecisionManager : AuthorizationManager<RequestAuthorizationContext> {
    private val matcher = AntPathMatcher()
    override fun check(
        authentication: Supplier<Authentication>,
        context: RequestAuthorizationContext
    ): AuthorizationDecision {
        val authorities = authentication.get().authorities
        val urlResources = authorities.filterIsInstance<PurviewInfo>()
            .filter { it.type === PurviewInfo.Type.URL }
            .toList()
        val uri = context.request.requestURI
        val method = context.request.method
        urlResources.forEach {
            if (it.methodEnums === HttpMethodEnums.ALL) {
                matcher.match(it.code, uri)
                return AuthorizationDecision(true)
            }
            if (it.methodEnums === convertMethodEnums(method)) {
                matcher.match(it.code, uri)
                return AuthorizationDecision(true)
            }
        }
        return AuthorizationDecision(false)
    }


}