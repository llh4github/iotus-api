package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.api.dao.UserDao
import io.github.llh4github.lotus.api.dto.UserAuthDetails
import io.github.llh4github.lotus.api.exceptions.AuthModuleException
import io.github.llh4github.lotus.model.auth.dto.UserAuthView
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 *
 *
 * Created At 2024/1/14 11:01
 * @author llh
 */
@Service
class UserDetailsServiceImpl(
    private val userDao: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val data = userDao.findByUsername(UserAuthView::class, username)
            ?: throw AuthModuleException.usernameNotFound("用户名或密码不正确")
        return UserAuthDetails(data)
    }
}