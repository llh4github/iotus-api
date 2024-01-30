package io.github.llh4github.lotus.model.auth


import io.github.llh4github.lotus.model.BaseDao
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Component

/**
 *
 *
 * Created At 2024/1/27 19:47
 * @author llh
 */
@Component
class RoleDao : BaseDao<Role>() {
    override val entityType = Role::class

    fun isNotExistCode(code: String, notId: Long? = null): Boolean {
        return !isExistCode(code, notId)
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
