package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * Created At 2024/1/16 10:19
 * @author llh
 */
@Tag(name = "用户信息操作")
@RestController
@RequestMapping("auth/user")
class UserApi(
    private val userService: UserService
) : BaseApi() {


}