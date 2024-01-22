package io.github.llh4github.lotus.api.vo.auth

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2024/1/15 15:02
 * @author llh
 */
@Schema(title = "登出所需参数")
data class LogoutParam(
    val accessToken: String,
    val refreshToken: String,
)
