package io.github.llh4github.lotus.model.auth

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 兼容前端的结构
 *
 * Created At 2024/1/24 10:16
 * @author llh
 */
data class MenuMetaVo(

    @get:Schema(title = "页面标题")
    val title: String,

    @get:Schema(title = "页面图标")
    val icon: String?,

    @get:Schema(title = "页面排序号")
    val rank: Int?

)
