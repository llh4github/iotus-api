package io.github.llh4github.lotus.api.utils

import io.github.llh4github.lotus.api.dto.UserAuthDetails
import org.springframework.security.core.context.SecurityContextHolder

/**
 *
 *
 * Created At 2024/1/15 18:07
 * @author llh
 */
object SecurityUtil {

    fun userAuthDetails(): UserAuthDetails {
        return SecurityContextHolder.getContext().authentication.principal as UserAuthDetails
    }

    fun currentUserId(): Long {
        return userAuthDetails().userId
    }

    fun currentUsername(): String {
        return userAuthDetails().username
    }
}