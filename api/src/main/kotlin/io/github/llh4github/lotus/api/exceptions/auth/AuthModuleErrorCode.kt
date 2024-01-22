package io.github.llh4github.lotus.api.exceptions.auth

import org.babyfish.jimmer.error.ErrorFamily
import org.babyfish.jimmer.error.ErrorField

/**
 *
 *
 * Created At 2024/1/16 18:03
 * @author llh
 */
@ErrorFamily
enum class AuthModuleErrorCode {

    USERNAME_NOT_FOUND,
}