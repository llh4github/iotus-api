package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.UserService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.auth.User
import io.github.llh4github.lotus.model.auth.dto.UserAddInput
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
 * Created At 2024/1/16 10:19
 * @author llh
 */
@Tag(name = "用户信息操作")
@RestController
@RequestMapping("auth/user")
class UserApi(
    private val userService: UserService
) : BaseApi() {

    @PostMapping
    @Operation(summary = "创建用户")
    fun add(@RequestBody @Validated dto: UserAddInput): JsonWrapper<User> {
        val saved = userService.add(dto)
        return ok(saved)
    }

}