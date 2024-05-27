package io.github.llh4github.iotus.security

import io.github.llh4github.iotus.commons.JsonWrapper
import io.github.llh4github.iotus.util.ServletUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler


internal class AuthenticationFailedHandler : AuthenticationEntryPoint {
    private val logger = KotlinLogging.logger {}
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        if (request.requestURI == "/error") {
            return
        }
        logger.debug(authException) { "认证失败: ${authException.message}，无法访问： ${request.requestURI}" }
        val json = JsonWrapper(
            code = "AUTHENTICATION_FAILED",
            module = "AUTH",
            data = null,
            msg = "权限不足"
        )
        ServletUtil.writeJson(response, json)
    }
}

internal class AccessDeniedHandlerImpl : AccessDeniedHandler {
    private val logger = KotlinLogging.logger {}

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        logger.debug(accessDeniedException) { "用户无权限访问 ${request.requestURI}" }
        val json = JsonWrapper(
            code = "ACCESS_DENIED",
            module = "AUTH",
            data = null,
            msg = "权限不足"
        )
        ServletUtil.writeJson(response, json)
    }
}

internal class AuthenticationFailureHandlerImpl : AuthenticationFailureHandler {
    private val logger = KotlinLogging.logger {}

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        logger.debug(exception) { "登录失败" }
        val json = JsonWrapper(
            code = "LOGIN_FAIL",
            module = "AUTH",
            data = "请检查用户名或密码是否正确",
            msg = "登录失败"
        )
        ServletUtil.writeJson(response, json)
    }

}