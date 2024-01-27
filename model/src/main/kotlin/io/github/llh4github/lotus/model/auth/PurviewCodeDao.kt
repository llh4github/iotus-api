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
class PurviewCodeDao : BaseDao<PurviewCode>() {
    override val entityType = PurviewCode::class
}
