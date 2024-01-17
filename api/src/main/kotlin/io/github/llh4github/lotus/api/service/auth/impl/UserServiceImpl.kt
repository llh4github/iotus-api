package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.UserDao
import io.github.llh4github.lotus.api.dao.fetchPage
import io.github.llh4github.lotus.api.service.auth.UserService
import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.User
import io.github.llh4github.lotus.model.auth.dto.CreateUserInput
import io.github.llh4github.lotus.model.auth.dto.UserSimpleQuerySpec
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

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

    override fun pageQuery(param: UserSimpleQuerySpec, fetcher: Fetcher<User>?): PageResult<User> {
        return dao.createQuery {
            where(param)
            select(table.fetch(fetcher))
        }.fetchPage(param.page)
    }

    override fun <S : View<User>> pageQuery(staticType: KClass<S>, param: UserSimpleQuerySpec): PageResult<S> {
        return dao.createQuery {
            where(param)
            select(table.fetch(staticType))
        }.fetchPage(param.page)
    }
}