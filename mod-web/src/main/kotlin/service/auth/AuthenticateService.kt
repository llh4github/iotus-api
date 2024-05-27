package io.github.llh4github.iotus.service.auth

import io.github.llh4github.iotus.vo.LoginParam
import io.github.llh4github.iotus.vo.LoginResult
import io.github.llh4github.iotus.vo.LogoutParam

/**
 * 认证服务类
 */
interface AuthenticateService {
    fun login(param: LoginParam): LoginResult
    fun logout(param: LogoutParam)
}