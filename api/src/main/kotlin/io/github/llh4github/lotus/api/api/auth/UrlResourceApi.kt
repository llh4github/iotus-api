package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.UrlResourceService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.api.vo.IdList
import io.github.llh4github.lotus.api.vo.IdSet
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.UrlResource
import io.github.llh4github.lotus.model.auth.dto.UrlReouceSpec
import io.github.llh4github.lotus.model.auth.dto.UrlResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.UrlResourceSimpleView
import io.github.llh4github.lotus.model.auth.dto.UrlResourceUpdateInput
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 *
 * Created At 2024/1/22 15:41
 * @author llh
 */
@Tag(name = "url资源数据")
@RestController
@RequestMapping("auth/resource/url")
class UrlResourceApi(
    private val urlResourceService: UrlResourceService
) : BaseApi() {

    @PostMapping("")
    @Operation(summary = "添加数据")
    fun add(@RequestBody @Validated dto: UrlResourceAddInput): JsonWrapper<UrlResource> {
        val rs = urlResourceService.add(dto)
        return ok(rs)
    }

    @PutMapping("")
    @Operation(summary = "更新数据")
    fun update(@RequestBody @Validated dto: UrlResourceUpdateInput): JsonWrapper<UrlResource> {
        val rs = urlResourceService.update(dto)
        return ok(rs)
    }

    @PostMapping("page")
    @Operation(summary = "分页查询")
    fun page(@RequestBody @Validated spec: UrlReouceSpec): JsonWrapper<PageResult<UrlResourceSimpleView>> {
        val rs = urlResourceService.pageQueryOutType(UrlResourceSimpleView::class, spec, spec.page)
        return ok(rs)
    }

    @DeleteMapping
    @Operation(summary = "删除数据")
    fun delete(@RequestBody @Validated idList:IdSet): JsonWrapper<Int> {
        val rs = urlResourceService.deleteById(idList.ids)
        return ok(rs)
    }
}
