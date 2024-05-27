package io.github.llh4github.iotus.service.auth.impl

import io.github.llh4github.iotus.security.UserAuthDetails
import io.github.llh4github.iotus.security.UserAuthToken
import io.github.llh4github.iotus.service.auth.AuthenticateService
import io.github.llh4github.iotus.service.security.TokenService
import io.github.llh4github.iotus.service.security.UserDetailsServiceImpl
import io.github.llh4github.iotus.vo.LoginParam
import io.github.llh4github.iotus.vo.LoginResult
import io.github.llh4github.iotus.vo.LogoutParam
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticateServiceImpl(
    private val tokenService: TokenService,
    private val userDetailsService: UserDetailsServiceImpl
) : AuthenticateService {
    private val logger = KotlinLogging.logger {}

    override fun login(param: LoginParam): LoginResult {
        val details = userDetailsService.loadUserByUsername(param.username) as UserAuthDetails
        val token = UserAuthToken(details, param.password)
        SecurityContextHolder.getContext().authentication = token
        logger.debug { "${param.username} 登录成功" }
        return LoginResult(
            username = details.username,
            accessToken = tokenService.createAccessToken(details),
            refreshToken = tokenService.createRefreshToken(details),
        )
    }

    override fun logout(param: LogoutParam) {
        SecurityContextHolder.getContext().authentication = null
        tokenService.banToken(param.accessToken)
        tokenService.banToken(param.refreshToken)
    }
}