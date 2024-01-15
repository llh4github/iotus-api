package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.dto.LoginParam
import io.github.llh4github.lotus.api.dto.LoginResult
import io.github.llh4github.lotus.api.dto.LogoutParam
import io.github.llh4github.lotus.api.service.AuthService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * Created At 2024/1/15 11:25
 * @author llh
 */
@Tag(name = "身份认证")
@RestController
@RequestMapping("auth")
class AuthApi(
    private val authService: AuthService,
) : BaseApi() {

    @Operation(summary = "登录接口")
    @PostMapping("login")
    fun login(@RequestBody @Validated param: LoginParam): JsonWrapper<LoginResult> {
        val rs = authService.login(param)
        return ok(rs)
    }

    @Operation(summary = "登出接口")
    @PostMapping("logout")
    fun logout(@RequestBody @Validated param: LogoutParam): JsonWrapper<Nothing> {
        authService.logout(param)
        return ok()
    }
}