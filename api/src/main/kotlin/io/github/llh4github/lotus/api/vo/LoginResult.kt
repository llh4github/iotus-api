package io.github.llh4github.lotus.api.vo

/**
 *
 *
 * Created At 2024/1/15 11:39
 * @author llh
 */
data class LoginResult(
    val username: String,
    val accessToken: String,
    val refreshToken: String,
)
