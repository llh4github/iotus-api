package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.vo.auth.LoginParam
import io.github.llh4github.lotus.api.vo.auth.LoginResult
import io.github.llh4github.lotus.api.vo.auth.LogoutParam

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