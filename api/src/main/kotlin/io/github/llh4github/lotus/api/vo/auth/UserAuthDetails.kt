package io.github.llh4github.lotus.api.vo.auth

import io.github.llh4github.lotus.api.security.PurviewInfo
import io.github.llh4github.lotus.model.auth.dto.UserAuthView
import org.springframework.security.core.userdetails.UserDetails

/**
 *
 *
 * Created At 2024/1/14 14:27
 * @author llh
 */
data class UserAuthDetails(
    val authInfo: UserAuthView
) : UserDetails {
    override fun getAuthorities(): List<PurviewInfo> {
        val roles = authInfo.roles.map { it.code }
            .map { PurviewInfo.role(it) }
            .toList()
        val url = authInfo.roles.flatMap { it.urlResources }
            .map { PurviewInfo.url(it.path, it.method) }
            .toList()
        val purview = authInfo.roles.flatMap { it.urlResources }
            .mapNotNull { it.purviewCode }
            .map { PurviewInfo.purviewCode(it.code) }
        return roles + purview + url
    }

    val userId = authInfo.id
    override fun getPassword(): String {
        return authInfo.password
    }

    override fun getUsername(): String {
        return authInfo.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
