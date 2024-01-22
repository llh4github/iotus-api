package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.UrlResourceDao
import io.github.llh4github.lotus.api.exceptions.UrlResourceException
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.UrlResourceService
import io.github.llh4github.lotus.model.HttpMethodEnums
import io.github.llh4github.lotus.model.auth.UrlResource
import io.github.llh4github.lotus.model.auth.dto.UrlResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.UrlResourceUpdateInput
import io.github.llh4github.lotus.model.auth.id
import io.github.llh4github.lotus.model.auth.method
import io.github.llh4github.lotus.model.auth.path
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Service

@Service
class UrlResourceServiceImpl(
    dao: UrlResourceDao
) : BaseServiceImpl<UrlResource, UrlResourceDao>(dao),
    UrlResourceService {
    private val logger = KotlinLogging.logger {}
    override fun add(dto: UrlResourceAddInput): UrlResource? {
        if (exitPath(dto.path, dto.method, null)) {
            throw UrlResourceException.pathDuplicate("url路径已存在", path = dto.path, method = dto.method)
        }
        return transactionTemplate.execute {
            baseDao.insert(dto)
        }
    }

    override fun update(dto: UrlResourceUpdateInput): UrlResource? {
        if (exitPath(dto.path, dto.method, dto.id)) {
            throw UrlResourceException.pathDuplicate("url路径已存在", path = dto.path, method = dto.method)
        }
        return transactionTemplate.execute {
            baseDao.update(dto)
        }
    }

    override fun exitPath(path: String, method: HttpMethodEnums, notId: Long?): Boolean {
        return baseDao.createQuery {
            where(table.path eq path)
            where(table.method eq method)
            notId?.let {
                where(table.id ne notId)
            }
            select(table)
        }.count() > 0
    }
}