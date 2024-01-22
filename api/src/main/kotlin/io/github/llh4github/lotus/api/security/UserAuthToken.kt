package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.api.vo.auth.UserAuthDetails
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
data class UserAuthToken(
    /** 存的是[UserAuthDetails] */
    private val principal: Any,
    /** eq password*/
    private val credentials: Any?,
    /** eq permission list */
    private val authorities: Collection<GrantedAuthority>? = null,
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

    val username: String by lazy { (principal as UserAuthDetails).username }
    val userId: Long by lazy { (principal as UserAuthDetails).userId }
}