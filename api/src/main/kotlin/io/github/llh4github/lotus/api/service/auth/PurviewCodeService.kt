package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.service.BaseService
import io.github.llh4github.lotus.model.auth.PurviewCode
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeAddInput
import io.github.llh4github.lotus.model.auth.dto.PurviewCodeUpdateInput

/**
 *
 *
 * Created At 2024/1/24 11:21
 * @author llh
 */
interface PurviewCodeService : BaseService<PurviewCode> {
    fun isExistCode(code:String,notId:Long?=null):Boolean
    fun add(dto: PurviewCodeAddInput): PurviewCode?
    fun update(dto: PurviewCodeUpdateInput): PurviewCode?
}