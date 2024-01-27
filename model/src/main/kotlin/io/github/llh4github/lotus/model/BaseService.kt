package io.github.llh4github.lotus.model

import org.babyfish.jimmer.Input
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification
import kotlin.reflect.KClass

/**
 * 基础服务类
 *
 * Created At 2024/1/27 16:54
 * @author llh
 */
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

    //region update add delete method

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
    //endregion
}