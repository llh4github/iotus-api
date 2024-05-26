package io.github.llh4github.iotus.dal.auth

import io.github.llh4github.iotus.dal.BaseModel
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "auth_user")
interface User : BaseModel {
    @Key
    val username: String
    val password: String
}