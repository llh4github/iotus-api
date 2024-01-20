package io.github.llh4github.lotus.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 *
 * Created At 2024/1/16 18:53
 * @author llh
 */
@Schema(title = "分页参数")
data class PageQueryParam(
    @Schema(title = "页码")
    val pageIndex: Int = 1,
    @Schema(title = "页大小")
    val pageSize: Int = 10,
) {
    // 查询页码从0开始
    @JsonIgnore
    val pageNum = if (pageIndex < 1) pageIndex else pageIndex - 1
}
