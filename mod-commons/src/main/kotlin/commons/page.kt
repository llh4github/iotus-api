package io.github.llh4github.iotus.commons

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "分页结果类")
data class PageResult<E>(
    @Schema(title = "数据总量")
    val totalRowCount: Long,
    @Schema(title = "总页数")
    val totalPage: Long,
    @Schema(title = "数据")
    val records: List<E>
)

@Schema(title = "分页参数")
data class PageQueryParam(
    @Schema(title = "页码")
    val pageIndex: Int = 1,
    @Schema(title = "页大小")
    val pageSize: Int = 10,
) {
    // 查询页码从0开始
    val pageNum = if (pageIndex < 1) pageIndex else pageIndex - 1
}