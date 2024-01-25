package io.github.llh4github.lotus.api.service.auth

import io.github.llh4github.lotus.api.service.BaseService
import io.github.llh4github.lotus.model.auth.MenuResource
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceAddWithPurviewInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceUpdateInput
import io.github.llh4github.lotus.model.auth.dto.MenuResourceUpdateWithPurviewInput
import org.babyfish.jimmer.View
import kotlin.reflect.KClass

/**
 *
 *
 * Created At 2024/1/23 18:24
 * @author llh
 */
interface MenuResourceService : BaseService<MenuResource> {
    fun add(dto: MenuResourceAddInput): MenuResource?
    fun update(dto: MenuResourceUpdateInput): MenuResource?
    fun isExistPath(path: String, notId: Long? = null): Boolean

    /**
     * 顶级菜单树列表
     *
     * parent_id 为空的。
     */
    fun <S : View<MenuResource>> topTree(staticType: KClass<S>): List<S>
    fun addWithPurview(dto: MenuResourceAddWithPurviewInput): MenuResource?
    suspend fun updateWithPurview(dto: MenuResourceUpdateWithPurviewInput): MenuResource?

}