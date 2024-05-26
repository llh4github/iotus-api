package io.github.llh4github.iotus.dal.auth

import io.github.llh4github.iotus.dal.BaseDao
import org.springframework.stereotype.Component

@Component
class UserDao : BaseDao<User>() {
    override val entityType = User::class
}
