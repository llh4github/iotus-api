package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.User
import io.github.llh4github.lotus.model.auth.username
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
class UserDao : BaseDao<User>() {
    override val entityType: KClass<User>
        get() = User::class


    fun findByUsername(username: String, fetcher: Fetcher<User>? = null): User? {
        return createQuery {
            where(table.username eq username)
            select(table.fetch(fetcher))
        }.fetchOneOrNull()
    }

    fun <S : View<User>> findByUsername(staticType: KClass<S>, username: String): S? {
        return createQuery {
            where(table.username eq username)
            select(table.fetch(staticType))
        }.fetchOneOrNull()
    }
}