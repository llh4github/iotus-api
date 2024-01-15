package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.api.dto.LoginParam
import io.github.llh4github.lotus.api.dto.LoginResult

/**
 *
 *
 * Created At 2024/1/15 11:33
 * @author llh
 */
interface AuthService {
    fun login(param: LoginParam): LoginResult
}