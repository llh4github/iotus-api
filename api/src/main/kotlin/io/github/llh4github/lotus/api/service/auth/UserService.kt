package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.service.BaseService
import io.github.llh4github.lotus.model.auth.User
import io.github.llh4github.lotus.model.auth.dto.UserAddInput

/**
 *
 *
 * Created At 2024/1/16 10:20
 * @author llh
 */
interface UserService : BaseService<User> {

    fun add(dto: UserAddInput): User?

}