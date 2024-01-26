package io.github.llh4github.lotus.model.auth

import io.github.llh4github.lotus.model.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.*

/**
 *
 *
 * Created At 2024/1/14 10:07
 * @author llh
 */
@Entity
@Table(name = "auth_role")
interface Role : BaseModel {

    @get:Schema(title = "角色名")
    val title: String

    @Key
    @get:Schema(title = "角色代号")
    val code: String

    @ManyToMany(mappedBy = "roles")
    val users: List<User>


    @ManyToMany
    @JoinTable(
        name = "auth_role_menu_resource_link",
        joinColumnName = "role_id",
        inverseJoinColumnName = "menu_resource_id"
    )
    val menuResources: List<MenuResource>
}
