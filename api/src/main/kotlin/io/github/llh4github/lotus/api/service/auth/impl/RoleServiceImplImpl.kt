package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.RoleDao
import io.github.llh4github.lotus.api.exceptions.RoleModuleException
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.RoleService
import io.github.llh4github.lotus.model.auth.Role
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
        if (baseDao.isExistCode(dto.code)) {
            throw RoleModuleException.roleCodeDuplicate("${dto.code} 己存在")
        }
        return transactionTemplate.execute {
            baseDao.insert(dto)
        }
    }

    override fun update(dto: RoleUpdateInput): Role? {
        if (baseDao.isExistCode(dto.code, dto.id)) {
            throw RoleModuleException.roleCodeDuplicate("${dto.code} 己存在")
        }
        return transactionTemplate.execute {
            baseDao.update(dto)
        }
    }
}