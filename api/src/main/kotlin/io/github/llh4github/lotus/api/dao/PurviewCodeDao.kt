package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.PurviewCode
import io.github.llh4github.lotus.model.auth.code
import io.github.llh4github.lotus.model.auth.id
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/24 11:20
 * @author llh
 */
@Component
class PurviewCodeDao : BaseDao<PurviewCode>() {
    override val entityType: KClass<PurviewCode>
        get() = PurviewCode::class

    fun isExistCodeList(codeList: List<String>): Boolean {
        return createQuery {
            where(table.code valueIn codeList)
            select(table)
        }.count() > 0
    }

    fun isExistCodeAndNotId(code: String, notId: Long): Boolean {
        return createQuery {
            where(table.code eq code)
            where(table.id ne notId)
            select(table)
        }.count() > 0
    }

    fun whichExistCode(codeList: List<String>): List<String> {
        if(codeList.isEmpty()) return emptyList()
        val queried = createQuery {
            where(table.code valueIn codeList)
            select(table.code)
        }.execute()
        return queried
    }
}