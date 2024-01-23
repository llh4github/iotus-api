package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.MenuResourceDao
import io.github.llh4github.lotus.api.exceptions.auth.MenuResourceException
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.MenuResourceService
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput
import io.github.llh4github.lotus.model.auth.id
import io.github.llh4github.lotus.model.auth.path
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Service

@Service
class MenuResourceServiceImpl(
    menuResourceDao: MenuResourceDao
) : BaseServiceImpl<MenuResource, MenuResourceDao>(menuResourceDao), MenuResourceService {
    override fun add(dto: MenuResourceAddInput): MenuResource? {
        if (isExistPath(dto.path)) {
            throw MenuResourceException.pathDuplicate("页面路径 ${dto.path} 已存在")
        }
        return transactionTemplate.execute {
            baseDao.save(dto)
        }
    }

    override fun isExistPath(path: String, notId: Long?): Boolean {
        return baseDao.createQuery {
            where(table.path eq path)
            notId?.let {
                where(table.id ne notId)
            }
            select(table)
        }.count() > 0
    }
}