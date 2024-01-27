package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.model.BaseService
import io.github.llh4github.lotus.model.HttpMethodEnums
import io.github.llh4github.lotus.model.auth.UrlResource
import io.github.llh4github.lotus.model.auth.dto.UrlResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.UrlResourceUpdateInput

/**
 *
 *
 * Created At 2024/1/22 15:36
 * @author llh
 */
interface UrlResourceService : BaseService<UrlResource> {
    fun add(dto: UrlResourceAddInput): UrlResource?
    fun update(dto: UrlResourceUpdateInput): UrlResource?
    fun exitPath(path: String, method: HttpMethodEnums, notId: Long?): Boolean
}