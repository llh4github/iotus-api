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
        return authInfo.roles
            .flatMap { it.menuResources }
            .flatMap { it.purviewCodes }
            .filter { it.urlPath != null && it.urlMethod != null }
            .map { UrlPurviewVo(it.urlPath!!, it.urlMethod!!) }
            .toList()
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
