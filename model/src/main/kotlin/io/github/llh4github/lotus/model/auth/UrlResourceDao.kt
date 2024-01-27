package io.github.llh4github.lotus.model.auth


import io.github.llh4github.lotus.model.BaseDao
import org.springframework.stereotype.Component

/**
 *
 *
 * Created At 2024/1/27 19:47
 * @author llh
 */
@Component
class UrlResourceDao : BaseDao<UrlResource>() {
    override val entityType = UrlResource::class
}
