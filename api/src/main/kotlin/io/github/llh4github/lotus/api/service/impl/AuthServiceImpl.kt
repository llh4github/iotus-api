package io.github.llh4github.lotus.api.service.impl

import io.github.llh4github.lotus.api.dto.LoginParam
import io.github.llh4github.lotus.api.dto.LoginResult
import io.github.llh4github.lotus.api.dto.LogoutParam
import io.github.llh4github.lotus.api.dto.UserAuthDetails
import io.github.llh4github.lotus.api.security.UserAuthToken
import io.github.llh4github.lotus.api.service.AuthService
import io.github.llh4github.lotus.api.service.TokenService
import io.github.llh4github.lotus.api.service.UserDetailsServiceImpl
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val tokenService: TokenService,
    private val userDetailsService: UserDetailsServiceImpl
) : AuthService {
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
        SecurityContextHolder.getContext().authentication = null
        tokenService.banToken(param.accessToken)
        tokenService.banToken(param.refreshToken)
    }
}