package io.github.llh4github.lotus.commons

import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * Created At 2023/12/30 20:51
 * @author llh
 */
@Schema(title = "通用响应结构（JSON）")
data class JsonWrapper<T>(
    @Schema(title = "响应码")
    val code: String,
    @Schema(title = "响应消息")
    val msg: String,
    @Schema(title = "响应数据")
    val data: T? = null,
    @Schema(title = "模块名", description = "在异常的情况下，此值才有意义")
    val module: String = "",
) {
    @Schema(title = "响应是否成功")
    val success = code === "OK"

    companion object {
        @JvmStatic
        fun <T> ok(msg: String, data: T?): JsonWrapper<T> {
            return JsonWrapper("OK", msg, data)
        }
    }

}
