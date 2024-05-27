package io.github.llh4github.iotus.dal.auth

import io.github.llh4github.iotus.dal.BaseModel
import io.github.llh4github.iotus.dal.PageRouterMeta
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Formula
import org.babyfish.jimmer.sql.*

@Entity
@Table(name = "auth_page_meta")
interface PageMeta : BaseModel {
    @Column(name = "page_path")
    @get:Schema(title = "页面路径")
    val path: String

    @Column(name = "page_title")
    @get:Schema(title = "页面标题")
    val title: String

    @Column(name = "page_rank")
    @get:Schema(title = "排序")
    val rank: Int

    @Column(name = "page_icon")
    @get:Schema(title = "页面图标")
    val icon: String

    @Key
    @Column(name = "page_name")
    @get:Schema(title = "页面名称")
    val name: String

    @ManyToOne
    val parent: PageMeta?

    @OneToMany(mappedBy = "parent")
    val children: List<PageMeta>

    @get:Schema(title = "页面路由元数据")
    @Formula(dependencies = ["title", "icon", "path", "rank"])
    val meta: PageRouterMeta
        get() {
            return PageRouterMeta(
                title = title,
                icon = icon,
                path = path,
                rank = rank,
                roles = mutableListOf(),
                auths = mutableListOf()
            )
        }
}