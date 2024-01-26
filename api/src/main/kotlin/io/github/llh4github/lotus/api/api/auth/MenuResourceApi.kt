package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.MenuResourceService
import io.github.llh4github.lotus.api.vo.IdSet
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 *
 * Created At 2024/1/23 18:26
 * @author llh
 */
@Tag(name = "前端菜单资源数据")
@RestController
@RequestMapping("auth/resource/menu")
class MenuResourceApi(
    private val menuResourceService: MenuResourceService
) : BaseApi() {

    @PostMapping("")
    @Operation(summary = "添加数据")
    fun add(
        @RequestBody @Validated dto: MenuResourceAddInput
    ): JsonWrapper<MenuResource> {
        val rs = menuResourceService.add(dto)
        return ok(rs)
    }

    @PostMapping("withPurview")
    @Operation(summary = "添加数据(带权限代号)")
    fun addWithPurview(
        @RequestBody @Validated dto: MenuResourceAddWithPurviewInput
    ): JsonWrapper<MenuResource> {
        val rs = menuResourceService.addWithPurview(dto)
        return ok(rs)
    }

    @PutMapping("withPurview")
    @Operation(summary = "更新数据(带权限代号)")
    suspend fun updateWithPurview(
        @RequestBody @Validated dto: MenuResourceUpdateWithPurviewInput
    ): JsonWrapper<MenuResource> {
        val rs = menuResourceService.updateWithPurview(dto)
        return ok(rs)
    }

    @PutMapping("")
    @Operation(summary = "更新数据")
    fun update(@RequestBody @Validated dto: MenuResourceUpdateInput): JsonWrapper<MenuResource> {
        val rs = menuResourceService.update(dto)
        return ok(rs)
    }

    @DeleteMapping
    @Operation(summary = "删除数据")
    fun delete(@RequestBody @Validated idSet: IdSet): JsonWrapper<Int> {
        val rs = menuResourceService.deleteById(idSet.ids)
        return ok(rs)
    }

    @GetMapping("")
    @Operation(summary = "查询简单信息")
    fun getById(id: Long): JsonWrapper<MenuResourceSimpleView> {
        val rs = menuResourceService.findById(MenuResourceSimpleView::class, id)
        return ok(rs)
    }

    @PostMapping("page")
    @Operation(summary = "分页查询")
    fun page(@RequestBody @Validated dto: MenuResourceSimpleSpec): JsonWrapper<PageResult<MenuResourceSimpleView>> {
        val rs = menuResourceService.pageQueryOutType(MenuResourceSimpleView::class, dto, dto.page)
        return ok(rs)
    }

    @PostMapping("page/withPurviewCode")
    @Operation(summary = "分页查询(带权限代号信息)")
    fun pageWithPurviewCode(
        @RequestBody @Validated dto: MenuResourceSimpleSpec
    ): JsonWrapper<PageResult<MenuResourcePurviewCodeView>> {
        val rs = menuResourceService.pageQueryOutType(MenuResourcePurviewCodeView::class, dto, dto.page)
        return ok(rs)
    }

    @GetMapping("tree")
    @Operation(summary = "指定的单棵菜单树")
    fun tree(id: Long): JsonWrapper<MenuResourceSimpleTreeView> {
        val rs = menuResourceService.findById(MenuResourceSimpleTreeView::class, id)
        return ok(rs)
    }

    @GetMapping("tree/top")
    @Operation(summary = "菜单树列表")
    fun topTree(): JsonWrapper<List<MenuResourceSimpleTreeView>> {
        val rs = menuResourceService.topTree(MenuResourceSimpleTreeView::class)
        return ok(rs)
    }
}