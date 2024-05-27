package io.github.llh4github.iotus.security

import io.github.llh4github.iotus.dal.auth.User
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserAuthDetails(
    val user: User
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf<AuthToken>()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }
    val userId = user.id
}

data class AuthToken(
    val token: String
) : GrantedAuthority {
    override fun getAuthority(): String {
        return token
    }
}

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

}
