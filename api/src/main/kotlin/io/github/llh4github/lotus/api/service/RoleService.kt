package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.model.auth.dto.RoleAddInput

/**
 *
 *
 * Created At 2024/1/16 11:00
 * @author llh
 */
interface RoleService {
    fun add(dto: RoleAddInput)
}