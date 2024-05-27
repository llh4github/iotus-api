package io.github.llh4github.iotus.service.security

import io.github.llh4github.iotus.dal.auth.UserDao
import io.github.llh4github.iotus.dal.auth.username
import io.github.llh4github.iotus.exception.auth.UserModuleException
import io.github.llh4github.iotus.security.UserAuthDetails
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userDao: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val data = userDao.createQuery {
            where(table.username eq username)
            select(table)
        }.fetchOneOrNull() ?: throw UserModuleException.usernameNotFound("用户名或密码不正确")
        return UserAuthDetails(data)
    }
}