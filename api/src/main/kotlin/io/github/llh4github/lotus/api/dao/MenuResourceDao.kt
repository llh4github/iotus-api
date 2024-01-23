package io.github.llh4github.lotus.api.dao

import io.github.llh4github.lotus.model.auth.MenuResource
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/23 18:23
 * @author llh
 */
@Component
class MenuResourceDao: BaseDao<MenuResource>() {
    override val entityType: KClass<MenuResource>
        get() = MenuResource::class
}