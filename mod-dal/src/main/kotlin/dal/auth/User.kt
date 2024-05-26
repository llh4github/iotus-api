package io.github.llh4github.iotus.dal.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import io.github.llh4github.iotus.dal.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "auth_user")
interface User : BaseModel {
    @Key
    @get:Schema(title = "用户名", example = "admin")
    val username: String

    @get:JsonIgnore
    @Column(name = "password_hash")
    val password: String
}