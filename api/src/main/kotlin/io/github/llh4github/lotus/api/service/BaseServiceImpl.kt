package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.api.dao.BaseDao
import io.github.llh4github.lotus.api.dao.fetchPage
import io.github.llh4github.lotus.model.BaseModel
import io.github.llh4github.lotus.model.PageQueryParam
import io.github.llh4github.lotus.model.PageResult
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionTemplate
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/17 10:37
 * @author llh
 */
abstract class BaseServiceImpl<E : BaseModel, M : BaseDao<E>>(
    protected val baseDao: M
) : BaseService<E> {

    @Autowired
    protected lateinit var transactionTemplate: TransactionTemplate
    override fun exist(id: Long): Boolean {
        return findById(id) != null
    }

    override fun notExist(id: Long): Boolean = !exist(id)
    override fun findById(id: Long, fetcher: Fetcher<E>?): E? {
        return baseDao.findById(id, fetcher)
    }

    override fun <S : View<E>> findById(staticType: KClass<S>, id: Long): S? {
        return baseDao.findById(staticType, id)
    }

    override fun findByIds(ids: List<Long>, fetcher: Fetcher<E>?): List<E> =
        baseDao.findByIds(ids, fetcher)

    override fun <S : View<E>> findByIds(staticType: KClass<S>, ids: List<Long>): List<S> {
        return baseDao.findByIds(staticType, ids)
    }

    override fun pageQuery(
        param: KSpecification<E>, pageIndex: Int, pageSize: Int, fetcher: Fetcher<E>?
    ): PageResult<E> {
        return pageQuery(param, PageQueryParam(pageIndex, pageSize), fetcher)
    }

    override fun pageQuery(
        queryParam: KSpecification<E>, pageParam: PageQueryParam, fetcher: Fetcher<E>?
    ): PageResult<E> {
        return baseDao.createQuery {
            where(queryParam)
            select(table.fetch(fetcher))
        }.fetchPage(pageParam)
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

    override fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageIndex: Int, pageSize: Int
    ): PageResult<S> {
        return pageQueryOutType(staticType, queryParam, PageQueryParam(pageIndex, pageSize))
    }

}