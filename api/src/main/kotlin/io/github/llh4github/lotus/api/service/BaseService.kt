package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.model.BaseModel
import io.github.llh4github.lotus.model.PageQueryParam
import io.github.llh4github.lotus.model.PageResult
import org.babyfish.jimmer.Input
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/17 11:07
 * @author llh
 */
interface BaseService<E : BaseModel> {
    fun exist(id: Long): Boolean
    fun notExist(id: Long): Boolean
    fun findById(id: Long, fetcher: Fetcher<E>? = null): E?
    fun <S : View<E>> findById(staticType: KClass<S>, id: Long): S?
    fun findByIds(ids: List<Long>, fetcher: Fetcher<E>?): List<E>
    fun <S : View<E>> findByIds(staticType: KClass<S>, ids: List<Long>): List<S>

    /**
     * 当前表基本更新操作。
     * 更复杂的业务操作另起方法。
     *
     *  默认直接更新数据。
     * @param checkOrException 对数据进行校验的方法。不符合的情况直接抛出异常。
     * @return 更新后的单表实体
     */
    fun <S : Input<E>> updateByInput(dto: S, checkOrException: (() -> Unit)? = null): E?

    /**
     * 当前表基本新增操作。
     * 更复杂的业务操作另起方法。
     *
     * 默认直接插入数据。
     * @param checkOrException 对数据进行校验的方法。不符合的情况直接抛出异常。
     * @return 新增的单表实体
     */
    fun <S : Input<E>> addByInput(dto: S, checkOrException: (() -> Unit)? = null): E?

    fun deleteById(ids: Collection<Long>): Int
    fun deleteById(id: Long): Int {
        return deleteById(listOf(id))
    }

    fun pageQuery(
        param: KSpecification<E>, pageIndex: Int, pageSize: Int,
        fetcher: Fetcher<E>? = null
    ): PageResult<E>

    fun pageQuery(
        queryParam: KSpecification<E>,
        pageParam: PageQueryParam,
        fetcher: Fetcher<E>? = null
    ): PageResult<E>

    fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageParam: PageQueryParam
    ): PageResult<S>

    fun <S : View<E>> pageQueryOutType(
        staticType: KClass<S>,
        queryParam: KSpecification<E>,
        pageIndex: Int, pageSize: Int
    ): PageResult<S>
}