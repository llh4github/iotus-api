package io.github.llh4github.lotus.model.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import io.github.llh4github.lotus.model.BaseModel
import org.babyfish.jimmer.sql.*

@Entity
@Table(name = "auth_user")
interface User : BaseModel {

    @Key
    val username: String

    val nickname: String

    @get:JsonIgnore
    val password: String

    @ManyToMany
    @JoinTable(
        name = "auth_user_role_link",
        joinColumnName = "user_id",
        inverseJoinColumnName = "role_id"
    )
    val roles: List<Role>
}
