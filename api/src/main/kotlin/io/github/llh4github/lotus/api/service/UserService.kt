package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.model.auth.dto.CreateUserInput

/**
 *
 *
 * Created At 2024/1/16 10:20
 * @author llh
 */
interface UserService {

    @Deprecated(message = "")
    fun add(dto: CreateUserInput)
}