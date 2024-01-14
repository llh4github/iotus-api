package io.github.llh4github.lotus.model.auth

import BaseModel
import com.fasterxml.jackson.annotation.JsonIgnore
import org.babyfish.jimmer.sql.*

@Entity
@Table(name = "auth_user")
interface User : BaseModel {
    @Id
    @GeneratedValue(generatorRef = "idUtil")
    @Column(name = "id")
    val userId: Long

    @Key
    val username: String

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
