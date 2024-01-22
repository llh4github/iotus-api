package io.github.llh4github.lotus.api.vo

import jakarta.validation.constraints.NotEmpty

/**
 * id 数据列表
 *
 * Created At 2024/1/22 16:39
 * @author llh
 */
data class IdList(
    @get:NotEmpty(message = "ID列表不能为空")
    val ids: List<Long>
)

data class IdSet(
    @get:NotEmpty(message = "ID列表不能为空")
    val ids: Set<Long>
)
