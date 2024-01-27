package io.github.llh4github.lotus.model

import jakarta.annotation.Resource
import org.babyfish.jimmer.Input
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
import org.babyfish.jimmer.sql.kt.ast.query.fetchPage
import org.springframework.data.repository.NoRepositoryBean
import java.sql.Connection
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/27 16:37
 * @author llh
 */
@NoRepositoryBean
abstract class BaseDao<E : BaseModel> {
    //region
    @Resource
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

    //endregion


    //region query method
    /**
     * 数据是否存在。true: 存在
     */
    fun exist(id: Long): Boolean {
        return createQuery {
            where(table.getId<Long>() eq id)
            select(table)
        }.count() > 0
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

    //endregion query method


    //region save or insert method

    fun save(entity: E, block: KSaveCommandDsl.() -> Unit): KSimpleSaveResult<E> =
        sqlClient.save(entity, block = block)

    fun save(entity: E): E = save(entity, SaveMode.UPSERT).modifiedEntity
    fun save(dto: Input<E>): E = save(dto.toEntity(), SaveMode.UPSERT).modifiedEntity

    fun update(dto: Input<E>): E = save(dto.toEntity(), SaveMode.UPDATE_ONLY).modifiedEntity

    fun insert(dto: Input<E>): E = save(dto.toEntity(), SaveMode.INSERT_ONLY).modifiedEntity
    fun insert(dto: E): E = save(dto, SaveMode.INSERT_ONLY).modifiedEntity

    fun save(entity: E, mode: SaveMode): KSimpleSaveResult<E> =
        save(entity) { setMode(mode) }

    //endregion

    fun delete(ids: Collection<Long>): Int {
        return sqlClient.deleteByIds(entityType, ids).totalAffectedRowCount
    }


}

fun <E> KConfigurableRootQuery<*, E>.fetchPage(
    pageParams: PageQueryParam,
    con: Connection? = null
): PageResult<E> {
    return this.fetchPage(pageParams.pageNum, pageParams.pageSize, con) { entities, totalCount, _ ->
        PageResult(
            totalCount,
            (totalCount + pageParams.pageNum) / pageParams.pageSize,
            entities,
        )
    }
}
