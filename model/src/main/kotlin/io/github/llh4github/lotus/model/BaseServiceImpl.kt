package io.github.llh4github.lotus.model

import org.babyfish.jimmer.Input
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionTemplate
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/27 19:08
 * @author llh
 */
abstract class BaseServiceImpl<E : BaseModel, M : BaseDao<E>>(
    protected val baseDao: M
) : BaseService<E> {
    @Autowired
    protected lateinit var transactionTemplate: TransactionTemplate
    override fun isExistById(id: Long): Boolean {
        return baseDao.exist(id)
    }

    override fun findById(id: Long, fetcher: Fetcher<E>?): E? {
        return baseDao.findById(id, fetcher)
    }

    override fun <S : View<E>> findById(staticType: KClass<S>, id: Long): S? {
        return baseDao.findById(staticType, id)
    }

    override fun findByIds(ids: List<Long>, fetcher: Fetcher<E>?): List<E> {
        return baseDao.findByIds(ids, fetcher)
    }

    override fun <S : View<E>> findByIds(staticType: KClass<S>, ids: List<Long>): List<S> {
        return baseDao.findByIds(staticType, ids)
    }

    override fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageParam: PageQueryParam
    ): PageResult<S> {
        return baseDao.createQuery {
            where(queryParam)
            select(table.fetch(staticType))
        }.fetchPage(pageParam)
    }


    override fun <S : Input<E>> updateByInput(dto: S, checkOrException: (() -> Unit)?): E? {
        checkOrException?.invoke()
        return transactionTemplate.execute {
            baseDao.update(dto)
        }
    }

    override fun <S : Input<E>> addByInput(dto: S, checkOrException: (() -> Unit)?): E? {
        checkOrException?.invoke()
        return transactionTemplate.execute {
            baseDao.insert(dto)
        }
    }

    override fun deleteById(ids: Collection<Long>): Int {
        return transactionTemplate.execute {
            baseDao.delete(ids)
        } ?: 0
    }
}