package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.api.utils.ServletUtil
import io.github.llh4github.lotus.commons.AppErrorEnums
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler


internal class AuthenticationFailedHandler : AuthenticationEntryPoint {
    private val logger = KotlinLogging.logger {}
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.debug(authException) { "认证失败: ${authException.message}，无法访问： ${request.requestURI}" }
        val json = JsonWrapper.response(AppErrorEnums.AUTH_FAILED, null)
        ServletUtil.writeJson(response, json)
    }
}

internal class LogoutSuccess : LogoutSuccessHandler {
    override fun onLogoutSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authentication: Authentication?
    ) {
        val json = JsonWrapper.ok("登出成功")
        ServletUtil.writeJson(response, json)
    }
}

internal class AccessDeniedHandlerImpl : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {

        val json = JsonWrapper.response(AppErrorEnums.NO_PERMISSION_ERROR, null)
        ServletUtil.writeJson(response, json)
    }
}