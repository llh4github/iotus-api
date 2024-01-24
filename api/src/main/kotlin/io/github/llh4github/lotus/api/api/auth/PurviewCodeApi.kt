package io.github.llh4github.lotus.api.api.auth

import io.github.llh4github.lotus.api.api.BaseApi
import io.github.llh4github.lotus.api.service.auth.PurviewCodeService
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.PurviewCode
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeAddInput
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeSimpleSpec
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeSimpleView
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeUpdateInput
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 *
 * Created At 2024/1/24 11:31
 * @author llh
 */
@Tag(name = "权限代号数据")
@RestController
@RequestMapping("auth/purviewCode")
class PurviewCodeApi(
    private val purviewCodeService: PurviewCodeService
) : BaseApi() {
    @PostMapping("page")
    @Operation(summary = "分页")
    fun page(@RequestBody @Validated spec: PurviewCodeSimpleSpec): JsonWrapper<PageResult<PurviewCodeSimpleView>> {
        val rs = purviewCodeService.pageQueryOutType(PurviewCodeSimpleView::class, spec, spec.page)
        return ok(rs)
    }

    @GetMapping
    @Operation(summary = "单个详情")
    fun getById(id: Long): JsonWrapper<PurviewCodeSimpleView> {
        val rs = purviewCodeService.findById(PurviewCodeSimpleView::class, id)
        return ok(rs)
    }

    @PostMapping
    @Operation(summary = "添加数据")
    fun add(@RequestBody @Validated dto: PurviewCodeAddInput): JsonWrapper<PurviewCode> {
       val rs = purviewCodeService.add(dto)
        return ok(rs)
    }

    @PutMapping
    @Operation(summary = "更新数据")
    fun update(@RequestBody @Validated dto: PurviewCodeUpdateInput): JsonWrapper<PurviewCode> {
        val rs = purviewCodeService.update(dto)
        return ok(rs)
    }
}