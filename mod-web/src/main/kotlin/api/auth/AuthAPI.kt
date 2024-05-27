package io.github.llh4github.iotus.api.auth

import io.github.llh4github.iotus.commons.JsonWrapper
import io.github.llh4github.iotus.service.auth.AuthenticateService
import io.github.llh4github.iotus.vo.LoginParam
import io.github.llh4github.iotus.vo.LoginResult
import io.github.llh4github.iotus.vo.LogoutParam
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "认证接口")
@RestController
@RequestMapping("auth")
class AuthAPI(
    private val authenticateService: AuthenticateService
) {

    @Operation(summary = "登录")
    @PostMapping("login")
    fun login(@RequestBody @Validated param: LoginParam): JsonWrapper<LoginResult> {
        val rs = authenticateService.login(param)
        return JsonWrapper.ok(rs)
    }

    @Operation(summary = "登出")
    @PostMapping("logout")
    fun logout(@RequestBody param: LogoutParam): JsonWrapper<String> {
        authenticateService.logout(param)
        return JsonWrapper.ok()
    }
}