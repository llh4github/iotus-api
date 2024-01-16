package io.github.llh4github.lotus.api.service.impl

import io.github.llh4github.lotus.api.dao.RoleDao
import io.github.llh4github.lotus.api.exceptions.RoleModuleException
import io.github.llh4github.lotus.api.service.RoleService
import io.github.llh4github.lotus.model.auth.dto.RoleAddInput
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val roleDao: RoleDao
) : RoleService {
    private val logger = KotlinLogging.logger {}
    override fun add(dto: RoleAddInput) {
        if (roleDao.isExistCode(dto.code)) {
            throw RoleModuleException.roleCodeDuplicate("${dto.code} 己存在")
        }
        roleDao.insert(dto)
    }
}