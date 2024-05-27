package io.github.llh4github.iotus.vo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

@Schema(title = "登录结果")
data class LoginResult(
    @Schema(title = "用户名")
    val username: String,
    @Schema(title = "访问令牌")
    val accessToken: String,
    @Schema(title = "刷新令牌")
    val refreshToken: String,
)

@Schema(title = "登录参数")
data class LoginParam(
    @NotEmpty(message = "用户名不能为空")
    @Schema(title = "用户名", example = "tom")
    val username: String,

    @Schema(title = "密码", example = "123456abcd")
    @NotEmpty(message = "密码不能为空")
    val password: String,
)

@Schema(title = "登出参数")
data class LogoutParam(
    @Schema(title = "访问令牌")
    val accessToken: String,
    @Schema(title = "刷新令牌")
    val refreshToken: String,
)