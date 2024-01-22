package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.UrlResource
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/15 16:35
 * @author llh
 */
@Component
class UrlResourceDao : BaseDao<UrlResource>() {
    override val entityType: KClass<UrlResource>
        get() = UrlResource::class

}