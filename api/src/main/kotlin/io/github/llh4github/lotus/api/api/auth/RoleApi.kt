package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.RoleService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.auth.dto.RoleAddInput
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
 * Created At 2024/1/16 11:07
 * @author llh
 */
@Tag(name = "角色信息接口")
@RestController
@RequestMapping("auth/role")
class RoleApi(
    private val roleService: RoleService
) : BaseApi() {

    @PostMapping
    @Operation(summary = "添加角色")
    fun add(@RequestBody @Validated dto: RoleAddInput): JsonWrapper<Nothing> {
        roleService.add(dto)
        return ok(null)
    }

}