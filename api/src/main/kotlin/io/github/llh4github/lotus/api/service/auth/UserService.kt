package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.model.PageResult
import io.github.llh4github.lotus.model.auth.User
import io.github.llh4github.lotus.model.auth.dto.CreateUserInput
import io.github.llh4github.lotus.model.auth.dto.UserSimpleQuerySpec
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.fetcher.Fetcher
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/16 10:20
 * @author llh
 */
interface UserService {

    @Deprecated(message = "")
    fun add(dto: CreateUserInput)

    fun pageQuery(param: UserSimpleQuerySpec, fetcher: Fetcher<User>? = null): PageResult<User>
    fun <S : View<User>> pageQuery(staticType: KClass<S>, param: UserSimpleQuerySpec): PageResult<S>
}