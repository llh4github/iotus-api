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
        val uri = context.request.requestURI
        val method = context.request.method
        val has = judgeUrlPurview(authentication, uri, method)
        return AuthorizationDecision(has)
    }

    private fun judgeUrlPurview(
        authentication: Supplier<Authentication>,
        url: String,
        method: String,
    ): Boolean {
        val authorities = authentication.get().authorities
        val urlPurviewList = authorities.filterIsInstance<UrlPurviewVo>()
            .toList()
        return urlPurviewList.any {
            if (it.method === HttpMethodEnums.ALL_METHOD) {
                return matcher.match(it.url, url)
            }
            if (it.method === convertMethodEnums(method)) {
                return matcher.match(it.url, url)
            }
            return false
        }
    }
}