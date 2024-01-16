package io.github.llh4github.lotus.model.auth

import io.github.llh4github.lotus.model.BaseModel
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

    val title: String

    @Key
    val code: String

    @ManyToMany(mappedBy = "roles")
    val users: List<User>


    @ManyToMany
    @JoinTable(
        name = "auth_role_url_resource_link",
        joinColumnName = "role_id",
        inverseJoinColumnName = "url_resource_id"
    )
    val urlResources: List<UrlResource>
}
