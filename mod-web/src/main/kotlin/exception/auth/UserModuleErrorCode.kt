package io.github.llh4github.iotus.exception.auth

import org.babyfish.jimmer.error.ErrorFamily

@ErrorFamily
enum class UserModuleErrorCode {
    USERNAME_NOT_FOUND,
}