package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.api.dao.UserDao
import io.github.llh4github.lotus.api.utils.SecurityUtil
import io.github.llh4github.lotus.model.HttpMethodEnums
import io.github.llh4github.lotus.model.auth.UrlResource
import io.github.llh4github.lotus.model.auth.dto.UserAuthView
import io.github.llh4github.lotus.model.convertMethodEnums
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.util.AntPathMatcher

internal class PermissionMetadataSource(
    private val userDao: UserDao,
) : FilterInvocationSecurityMetadataSource {
    private val matcher = AntPathMatcher()
    override fun getAttributes(obj: Any): List<ConfigAttribute> {
        val invocation = obj as FilterInvocation
        val uri = invocation.httpRequest.requestURI
        val method = invocation.httpRequest.method
        return verifyUrlPermission(uri, method)
    }

    // todo 应该是这个url -> 对应权限数据
    private fun verifyUrlPermission(uri: String, method: String): List<ConfigAttribute> {
        return allUrlResources().filter {
            if (it.method === HttpMethodEnums.ALL) {
                return@filter matcher.match(it.path, uri)
            }
            if (it.method === convertMethodEnums(method)) {
                return@filter matcher.match(it.path, uri)
            }
            false
        }.map { SecurityConfig(it.path) }
            .toList()

    }

    private fun allUrlResources(): List<UrlResource> {
        val username = SecurityUtil.currentUsername()
        val authView = userDao.findByUsername(UserAuthView::class, username) ?: return emptyList()
        return authView.roles.flatMap { it.urlResources }
            .map { it.toEntity() }
            .toList()
    }

    override fun getAllConfigAttributes(): List<ConfigAttribute> {
        return emptyList()
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return true
    }
}