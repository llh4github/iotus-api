package io.github.llh4github.lotus.api.service.auth.impl

import io.github.llh4github.lotus.api.dao.PurviewCodeDao
import io.github.llh4github.lotus.api.exceptions.auth.PurviewCodeException
import io.github.llh4github.lotus.api.service.BaseServiceImpl
import io.github.llh4github.lotus.api.service.auth.PurviewCodeService
import io.github.llh4github.lotus.model.auth.PurviewCode
import io.github.llh4github.lotus.model.auth.code
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeAddInput
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeUpdateInput
import io.github.llh4github.lotus.model.auth.id
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ne
import org.springframework.stereotype.Service

@Service
class PurviewCodeServiceImpl(
    dao: PurviewCodeDao,
) : BaseServiceImpl<PurviewCode, PurviewCodeDao>(dao), PurviewCodeService {
    override fun isExistCode(code: String, notId: Long?): Boolean {
        return baseDao.createQuery {
            where(table.code eq code)
            notId?.let {
                where(table.id ne it)
            }
            select(table)
        }.count() > 0
    }

    override fun add(dto: PurviewCodeAddInput): PurviewCode? {
        if (isExistCode(dto.code)) {
            throw PurviewCodeException.purviewCodeDuplicate("${dto.code} 已存在")
        }
        return transactionTemplate.execute {
            baseDao.save(dto)
        }
    }

    override fun update(dto: PurviewCodeUpdateInput): PurviewCode? {
        if (isExistCode(dto.code)) {
            throw PurviewCodeException.purviewCodeDuplicate("${dto.code} 已存在")
        }
        return transactionTemplate.execute {
            baseDao.update(dto)
        }
    }
}