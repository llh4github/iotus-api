package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.dto.LoginParam
import io.github.llh4github.lotus.api.dto.LoginResult
import io.github.llh4github.lotus.api.dto.LogoutParam

/**
 * 认证服务类
 *
 * Created At 2024/1/15 11:33
 * @author llh
 */
interface AuthenticateService {
    fun login(param: LoginParam): LoginResult
    fun logout(param: LogoutParam)
}