package io.github.llh4github.iotus.security

import io.github.llh4github.iotus.commons.JsonWrapper
import io.github.llh4github.iotus.conf.SecurityProperties
import io.github.llh4github.iotus.util.ServletUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val securityProperties: SecurityProperties,
) : OncePerRequestFilter() {
    private val matcher = AntPathMatcher()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if ("OPTIONS".equals(request.method, true)) {
            filterChain.doFilter(request, response)
            return
        }
        val hasAnno = securityProperties.annoUrl.any { matcher.match(it, request.requestURI) }
        if (hasAnno) {
            filterChain.doFilter(request, response)
            return
        }
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            val json = JsonWrapper(
                code = "NO_TOKEN",
                module = "AUTH",
                msg = "用户未登录",
                data = null
            )
            ServletUtil.writeJson(response, json)
            return
        }
        val jwt = authHeader.substring(7)

    }
}