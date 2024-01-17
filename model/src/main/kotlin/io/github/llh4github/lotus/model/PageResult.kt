package io.github.llh4github.lotus.model

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2024/1/17 10:15
 * @author llh
 */
@Schema(title = "分页结果类")
data class PageResult<E>(
    @Schema(title = "数据总量")
    val totalRowCount: Long,
    @Schema(title = "总页数")
    val totalPage: Long,
    @Schema(title = "数据")
    val records: List<E>
)
