package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.service.BaseService
import io.github.llh4github.lotus.model.auth.Role
import io.github.llh4github.lotus.model.auth.dto.RoleAddInput

/**
 *
 *
 * Created At 2024/1/16 11:00
 * @author llh
 */
interface RoleService : BaseService<Role> {
    fun add(dto: RoleAddInput)
}