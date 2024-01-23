package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.MenuResourceService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceSimpleSpec
import io.github.llh4github.lotus.model.auth.dto.MenuResourceSimpleView
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
    fun add(@RequestBody @Validated dto: MenuResourceAddInput): JsonWrapper<MenuResource> {
        val rs = menuResourceService.add(dto)
        return ok(rs)
    }

    @PostMapping("page")
    @Operation(summary = "分页查询")
    fun page(@RequestBody @Validated dto: MenuResourceSimpleSpec): JsonWrapper<PageResult<MenuResourceSimpleView>> {
        val rs = menuResourceService.pageQueryOutType(MenuResourceSimpleView::class, dto, dto.page)
        return ok(rs)
    }

}