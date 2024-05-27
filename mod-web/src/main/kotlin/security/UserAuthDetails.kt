package io.github.llh4github.iotus.security

import io.github.llh4github.iotus.dal.auth.User
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
}

data class AuthToken(
    val token: String
) : GrantedAuthority {
    override fun getAuthority(): String {
        return token
    }
}