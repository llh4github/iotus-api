package io.github.llh4github.lotus.model.auth


import io.github.llh4github.lotus.model.BaseDao
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/27 19:47
 * @author llh
 */
@Component
class UserDao : BaseDao<User>() {
    override val entityType = User::class
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
