package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.Role
import io.github.llh4github.lotus.model.auth.code
import io.github.llh4github.lotus.model.auth.id
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/16 10:15
 * @author llh
 */
@Component
class RoleDao : BaseDao<Role>() {
    private val logger = KotlinLogging.logger {}

    override val entityType: KClass<Role>
        get() = Role::class

    fun isNotExistCode(code: String): Boolean {
        return !isExistCode(code)
    }

    fun isExistCode(code: String, notId: Long? = null): Boolean {
        return createQuery {
            where(table.code eq code)
            notId?.let {
                where(table.id ne notId)
            }
            select(table)
        }.count() > 0
    }

}