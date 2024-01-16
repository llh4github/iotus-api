package io.github.llh4github.lotus.api.service.impl

import io.github.llh4github.lotus.api.dao.UserDao
import io.github.llh4github.lotus.api.service.UserService
import io.github.llh4github.lotus.model.auth.dto.CreateUserInput
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val dao: UserDao,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    private val logger = KotlinLogging.logger {}

    override fun add(dto: CreateUserInput) {
        val saveDto = dto.copy(password = passwordEncoder.encode(dto.password))
        dao.insert(saveDto)
    }
}