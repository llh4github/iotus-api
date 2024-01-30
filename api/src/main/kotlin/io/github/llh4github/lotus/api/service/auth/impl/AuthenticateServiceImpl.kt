package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.vo.auth.LoginParam
import io.github.llh4github.lotus.api.vo.auth.LoginResult
import io.github.llh4github.lotus.api.vo.auth.LogoutParam
import io.github.llh4github.lotus.api.security.UserAuthToken
import io.github.llh4github.lotus.api.service.auth.AuthenticateService
import io.github.llh4github.lotus.api.service.security.TokenService
import io.github.llh4github.lotus.api.service.security.UserDetailsServiceImpl
import io.github.llh4github.lotus.api.vo.auth.UserAuthDetails
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
            accessToken = tokenService.createToken(details),
            refreshToken = tokenService.createRefreshToken(details),
        )
    }

    override fun logout(param: LogoutParam) {
        SecurityContextHolder.clearContext()
        tokenService.banToken(param.accessToken)
        tokenService.banToken(param.refreshToken)
    }
}