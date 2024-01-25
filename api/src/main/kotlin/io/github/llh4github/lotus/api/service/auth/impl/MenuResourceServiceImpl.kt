package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.MenuResourceDao
import io.github.llh4github.lotus.api.dao.PurviewCodeDao
import io.github.llh4github.lotus.api.exceptions.auth.MenuResourceException
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.MenuResourceService
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddWithPurviewInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceUpdateInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceUpdateWithPurviewInput
import io.github.llh4github.lotus.model.auth.id
import io.github.llh4github.lotus.model.auth.parentId
import io.github.llh4github.lotus.model.auth.path
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.babyfish.jimmer.View
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.isNull
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class MenuResourceServiceImpl(
    menuResourceDao: MenuResourceDao,
    private val purviewCodeDao: PurviewCodeDao,
) : BaseServiceImpl<MenuResource, MenuResourceDao>(menuResourceDao), MenuResourceService {
    override fun add(dto: MenuResourceAddInput): MenuResource? {
        if (isExistPath(dto.path)) {
            throw MenuResourceException.pathDuplicate("页面路径 ${dto.path} 已存在")
        }
        return transactionTemplate.execute {
            baseDao.save(dto)
        }
    }

    override fun addWithPurview(dto: MenuResourceAddWithPurviewInput): MenuResource? {
        val existCodes = purviewCodeDao.whichExistCode(dto.purviewCodes.map { it.code })
        if (existCodes.isNotEmpty()) {
            throw MenuResourceException.purviewCodeDuplicate(
                message = "存在重复的权限代号",
                existPurviewCode = existCodes
            )
        }
        return transactionTemplate.execute {
            baseDao.save(dto)
        }
    }

    override suspend fun updateWithPurview(dto: MenuResourceUpdateWithPurviewInput): MenuResource? {
        val existCodes = mutableListOf<String>()
        coroutineScope {
            dto.purviewCodes.forEach {
                launch {
                    if (purviewCodeDao.isExistCodeAndNotId(it.code, it.id)) {
                        existCodes.add(it.code)
                    }
                }
            }
        }
        if (existCodes.isNotEmpty()) {
            throw MenuResourceException.purviewCodeDuplicate(
                message = "存在重复的权限代号",
                existPurviewCode = existCodes
            )
        }
        return transactionTemplate.execute {
            baseDao.update(dto)
        }
    }

    override fun update(dto: MenuResourceUpdateInput): MenuResource? {
        if (isExistPath(dto.path, dto.id)) {
            throw MenuResourceException.pathDuplicate("页面路径 ${dto.path} 已存在")
        }
        return transactionTemplate.execute {
            baseDao.update(dto)
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