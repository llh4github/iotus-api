package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.api.service.security.TokenService
import io.github.llh4github.lotus.api.utils.ServletUtil
import io.github.llh4github.lotus.commons.JsonWrapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 *
 *
 * Created At 2024/1/14 22:48
 * @author llh
 */
@Component
class JwtAuthenticationFilter(
    private val tokenService: TokenService,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
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
        if (tokenService.isInvalid(jwt)) {
            SecurityContextHolder.getContext().authentication = null
            val json = JsonWrapper(
                code = "TOKEN_ERROR",
                module = "AUTH",
                msg = "用户未登录或登录凭证已过期",
                data = null
            )
            ServletUtil.writeJson(response, json)
            return
        }
        val username = tokenService.parserUsername(jwt)
        if (username.isNotBlank() && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)
            val token = UserAuthToken(userDetails, userDetails.authorities)
            token.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = token
        }
        filterChain.doFilter(request, response)
    }
}