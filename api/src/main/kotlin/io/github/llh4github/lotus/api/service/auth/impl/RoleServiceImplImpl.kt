package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.exceptions.auth.RoleException
import io.github.llh4github.lotus.api.service.auth.RoleService
import io.github.llh4github.lotus.model.BaseServiceImpl
import io.github.llh4github.lotus.model.auth.Role
import io.github.llh4github.lotus.model.auth.RoleDao
import io.github.llh4github.lotus.model.auth.dto.RoleAddInput
import io.github.llh4github.lotus.model.auth.dto.RoleUpdateInput
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class RoleServiceImplImpl(
    roleDao: RoleDao,
) : BaseServiceImpl<Role, RoleDao>(roleDao), RoleService {
    private val logger = KotlinLogging.logger {}
    override fun add(dto: RoleAddInput): Role? {
        return addByInput(dto) {
            if (baseDao.isExistCode(dto.code)) {
                throw RoleException.roleCodeDuplicate("${dto.code} 己存在")
            }
        }
    }

    override fun update(dto: RoleUpdateInput): Role? {
        return updateByInput(dto) {
            if (baseDao.isExistCode(dto.code, dto.id)) {
                throw RoleException.roleCodeDuplicate("${dto.code} 己存在")
            }
        }
    }
}