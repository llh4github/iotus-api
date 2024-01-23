package io.github.llh4github.lotus.model.auth

import io.github.llh4github.lotus.model.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.Table

/**
 *
 *
 * Created At 2024/1/23 18:18
 * @author llh
 */
@Entity
@Table(name = "auth_menu_resource")
interface MenuResource : BaseModel {
    @get:Schema(title = "页面标题")
    val title: String

    @get:Schema(title = "页面图标")
    val icon: String?

    @get:Schema(title = "页面排序号")
    val rank: Int?

    @Key
    @get:Schema(title = "页面路径")
    val path: String

    @get:Schema(title = "页面名称（英文）")
    val name: String
}