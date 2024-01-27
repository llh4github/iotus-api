package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.exceptions.auth.MenuResourceException
import io.github.llh4github.lotus.api.service.auth.MenuResourceService
import io.github.llh4github.lotus.model.BaseServiceImpl
import io.github.llh4github.lotus.model.auth.*
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceUpdateInput
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class MenuResourceServiceImpl(
    menuResourceDao: MenuResourceDao
) : BaseServiceImpl<MenuResource, MenuResourceDao>(menuResourceDao), MenuResourceService {
    override fun add(dto: MenuResourceAddInput): MenuResource? {
        return addByInput(dto) {
            if (isExistPath(dto.path)) {
                throw MenuResourceException.pathDuplicate("页面路径 ${dto.path} 已存在")
            }
        }
    }

    override fun update(dto: MenuResourceUpdateInput): MenuResource? {
        return updateByInput(dto) {
            if (isExistPath(dto.path, dto.id)) {
                throw MenuResourceException.pathDuplicate("页面路径 ${dto.path} 已存在")
            }
        }
    }

    override fun <S : View<MenuResource>> topTree(staticType: KClass<S>): List<S> {
        return baseDao.createQuery {
            where(table.parentId.isNull())
            select(table.fetch(staticType))
        }.execute()
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