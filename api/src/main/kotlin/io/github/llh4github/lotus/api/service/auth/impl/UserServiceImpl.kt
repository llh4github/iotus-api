package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.UserDao
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.UserService
import io.github.llh4github.lotus.model.auth.User
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    dao: UserDao,
    private val passwordEncoder: PasswordEncoder
) : BaseServiceImpl<User, UserDao>(dao), UserService {
    private val logger = KotlinLogging.logger {}

}