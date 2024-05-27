package io.github.llh4github.iotus.dal

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "页面路由元数据")
data class PageRouterMeta(
    @Schema(title = "页面标题")
    val title: String,
    @Schema(title = "页面图标")
    val icon: String,
    @Schema(title = "页面路径")
    val path: String,
    @Schema(title = "页面排序")
    val rank: Int = 5,
    @Schema(title = "页面可访问角色")
    val roles: MutableList<String> = mutableListOf(),
    @Schema(title = "页面权限")
    val auths: MutableList<String> = mutableListOf(),
)
