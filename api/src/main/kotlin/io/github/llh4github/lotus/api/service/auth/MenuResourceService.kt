package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.service.BaseService
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput

/**
 *
 *
 * Created At 2024/1/23 18:24
 * @author llh
 */
interface MenuResourceService : BaseService<MenuResource> {
    fun add(dto: MenuResourceAddInput): MenuResource?

    fun isExistPath(path: String, notId: Long? = null): Boolean
}