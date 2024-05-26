package io.github.llh4github.iotus.service

import io.github.llh4github.iotus.commons.PageQueryParam
import io.github.llh4github.iotus.commons.PageResult
import io.github.llh4github.iotus.dal.BaseModel
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import kotlin.reflect.KClass

interface BaseService<E : BaseModel> {
    //region query

    fun isExistById(id: Long): Boolean
    fun isNotExistById(id: Long): Boolean = !isExistById(id)

    fun findById(id: Long, fetcher: Fetcher<E>? = null): E?
    fun <S : View<E>> findById(staticType: KClass<S>, id: Long): S?

    fun findByIds(ids: List<Long>, fetcher: Fetcher<E>?): List<E>
    fun <S : View<E>> findByIds(staticType: KClass<S>, ids: List<Long>): List<S>

    fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageParam: PageQueryParam
    ): PageResult<S>

    fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageIndex: Int, pageSize: Int
    ): PageResult<S> = pageQueryOutType(staticType, queryParam, PageQueryParam(pageIndex, pageSize))

    //endregion
}