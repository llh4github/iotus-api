package io.github.llh4github.lotus.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

/**
 *
 *
 * Created At 2024/1/15 11:28
 * @author llh
 */
@Schema(title = "登录参数")
data class LoginParam(
    @NotEmpty
    val username: String,
    @NotEmpty
    val password: String,
)
