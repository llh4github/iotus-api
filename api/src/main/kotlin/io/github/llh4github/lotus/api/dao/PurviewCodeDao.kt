package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.PurviewCode
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/24 11:20
 * @author llh
 */
@Component
class PurviewCodeDao : BaseDao<PurviewCode>() {
    override val entityType: KClass<PurviewCode>
        get() = PurviewCode::class
}