package io.github.llh4github.lotus.model.auth


import io.github.llh4github.lotus.model.BaseDao
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/27 19:47
 * @author llh
 */
@Component
class MenuResourceDao : BaseDao<MenuResource>() {
    override val entityType: KClass<MenuResource>
        get() = MenuResource::class
}
