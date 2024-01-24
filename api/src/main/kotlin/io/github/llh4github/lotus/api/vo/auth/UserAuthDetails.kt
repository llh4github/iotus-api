package io.github.llh4github.lotus.api.vo.auth

import io.github.llh4github.lotus.api.security.UrlPurviewVo
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
    override fun getAuthorities(): List<UrlPurviewVo> {
        val purview = authInfo.roles.flatMap { it.urlResources }
            .map { UrlPurviewVo(it.path, it.method, it.code) }
            .toList()
        return purview
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
