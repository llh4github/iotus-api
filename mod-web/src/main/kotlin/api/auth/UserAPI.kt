package io.github.llh4github.iotus.api.auth

import io.github.llh4github.iotus.commons.JsonWrapper
import io.github.llh4github.iotus.dal.auth.User
import io.github.llh4github.iotus.service.auth.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Tag(name = "用户数据接口")
@RestController
@RequestMapping("auth/user")
class UserAPI(
    private val userService: UserService
) {

    @GetMapping("id")
    @Operation(summary = "获取用户简单信息")
    fun simpleInfo(@RequestParam("id") id: Long): JsonWrapper<User> {
        val rs = userService.findById(id)
        return JsonWrapper.ok(rs)
    }
}