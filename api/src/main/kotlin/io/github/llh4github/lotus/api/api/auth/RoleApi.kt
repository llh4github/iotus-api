package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.RoleService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.Role
import io.github.llh4github.lotus.model.auth.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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
    fun add(@RequestBody @Validated dto: RoleAddInput): JsonWrapper<Role> {
        val rs = roleService.add(dto)
        return ok(rs)
    }

    @PutMapping
    @Operation(summary = "修改角色")
    fun update(@RequestBody @Validated dto: RoleUpdateInput): JsonWrapper<Role> {
        val rs = roleService.update(dto)
        return ok(rs)
    }

    @Operation(summary = "分页查询(简易信息)")
    @PostMapping("page")
    fun pageQuery(@RequestBody @Validated query: RoleSimpleSpec): JsonWrapper<PageResult<RoleSimpleView>> {
        val rs = roleService.pageQueryOutType(RoleSimpleView::class, query, query.page)
        return ok(rs)
    }

    @Operation(summary = "角色信息（简易）")
    @GetMapping("")
    fun getById(id: Long): JsonWrapper<Role> {
        val rs = roleService.findById(id)
        return ok(rs)
    }

    @Operation(summary = "角色信息（含对应权限）")
    @GetMapping("withPermission")
    fun getWithPermission(id: Long): JsonWrapper<RoleWithPermissionView> {
        val rs = roleService.findById(RoleWithPermissionView::class, id)
        return ok(rs)
    }

    @Operation(summary = "删除角色信息")
    @DeleteMapping("")
    fun delete(id: Long): JsonWrapper<Int> {
        val rs = roleService.deleteById(id)
        return ok(rs)
    }

}