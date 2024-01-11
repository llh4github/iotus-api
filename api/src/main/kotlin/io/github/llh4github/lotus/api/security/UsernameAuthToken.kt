package io.github.llh4github.lotus.api.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 *用户名密码身份令牌
 *
 * *不用SpringSecurity提供的，方便做拓展*
 *
 * Created At 2024/1/11 16:53
 * @author llh
 */
data class UsernameAuthToken(
    /** eq username */
    val principal: Any,
    /** eq password*/
    val credentials: Any?,
    /** eq permission list */
    val authorities: Collection<GrantedAuthority>? = null,
) : AbstractAuthenticationToken(authorities) {

    constructor(principal: Any, credentials: Any) : this(principal, credentials, null) {
        isAuthenticated = false
    }

    constructor(principal: Any, authorities: Collection<GrantedAuthority>) : this(principal, null, authorities) {
        isAuthenticated = true
    }

    override fun getCredentials(): Any? {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }

    val username: String by lazy { principal as String }
    val password: String by lazy { if (credentials == null) "" else credentials as String }
}