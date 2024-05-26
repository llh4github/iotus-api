package io.github.llh4github.iotus.service

import io.github.llh4github.iotus.commons.PageQueryParam
import io.github.llh4github.iotus.commons.PageResult
import io.github.llh4github.iotus.dal.BaseDao
import io.github.llh4github.iotus.dal.BaseModel
import io.github.llh4github.iotus.dal.fetchPage
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionTemplate
import kotlin.reflect.KClass


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
}