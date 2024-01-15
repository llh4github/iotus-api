package io.github.llh4github.lotus.api.dao

import BaseModel
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.ast.mutation.SaveMode
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.KExecutable
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.babyfish.jimmer.sql.kt.ast.mutation.KMutableDelete
import org.babyfish.jimmer.sql.kt.ast.mutation.KMutableUpdate
import org.babyfish.jimmer.sql.kt.ast.mutation.KSaveCommandDsl
import org.babyfish.jimmer.sql.kt.ast.mutation.KSimpleSaveResult
import org.babyfish.jimmer.sql.kt.ast.query.KConfigurableRootQuery
import org.babyfish.jimmer.sql.kt.ast.query.KMutableRootQuery
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.NoRepositoryBean
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/14 14:36
 * @author llh
 */
@NoRepositoryBean
abstract class BaseDao<E : BaseModel> {

    @Autowired
    protected lateinit var sqlClient: KSqlClient

    abstract val entityType: KClass<E>

    fun <R> createQuery(
        block: KMutableRootQuery<E>.() -> KConfigurableRootQuery<E, R>
    ): KConfigurableRootQuery<E, R> =
        sqlClient.createQuery(entityType, block)

    fun createUpdate(
        block: KMutableUpdate<E>.() -> Unit
    ): KExecutable<Int> =
        sqlClient.createUpdate(entityType, block)

    fun createDelete(
        block: KMutableDelete<E>.() -> Unit
    ): KExecutable<Int> =
        sqlClient.createDelete(entityType, block)

    /**
     * 数据是否存在。true: 存在
     */
    fun exist(id: Long): Boolean {
        return sqlClient.findById(entityType, id) !== null
    }

    /**
     * 数据是否不存在。true: 不存在
     */
    fun notExist(id: Long): Boolean {
        return !exist(id)
    }

    fun findById(id: Long, fetcher: Fetcher<E>? = null): E? {
        return if (fetcher !== null) {
            sqlClient.findById(fetcher, id)
        } else {
            sqlClient.findById(entityType, id)
        }
    }

    fun <S : View<E>> findById(staticType: KClass<S>, id: Long): S? {
        return createQuery {
            where(table.getId<Long>() eq id)
            select(table.fetch(staticType))
        }.fetchOneOrNull()
    }

    protected fun <S : View<E>> specQuery(
        spec: KSpecification<E>,
        staticType: KClass<S>
    ): KConfigurableRootQuery<E, S> {
        return createQuery {
            where(spec)
            select(table.fetch(staticType))
        }
    }

    fun findByIds(ids: List<Long>, fetcher: Fetcher<E>?): List<E> =
        if (fetcher !== null) {
            sqlClient.findByIds(fetcher, ids)
        } else {
            sqlClient.findByIds(entityType, ids)
        }

    fun <S : View<E>> findByIds(staticType: KClass<S>, ids: List<Long>): List<S> {
        return createQuery {
            where(table.getId<Long>() valueIn ids)
            select(table.fetch(staticType))
        }.execute()
    }

    fun save(entity: E, block: KSaveCommandDsl.() -> Unit): KSimpleSaveResult<E> =
        sqlClient.save(entity, block = block)

    fun save(entity: E): E = save(entity, SaveMode.UPSERT).modifiedEntity

    fun save(entity: E, mode: SaveMode): KSimpleSaveResult<E> =
        save(entity) { setMode(mode) }

}