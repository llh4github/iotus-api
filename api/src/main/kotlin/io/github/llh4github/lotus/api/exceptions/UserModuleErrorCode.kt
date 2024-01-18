package io.github.llh4github.lotus.api.exceptions

import org.babyfish.jimmer.error.ErrorFamily
import org.babyfish.jimmer.error.ErrorField

/**
 *
 *
 * Created At 2024/1/16 16:47
 * @author llh
 */
@ErrorFamily
enum class UserModuleErrorCode {
    @ErrorField(name = "name", type = String::class)
    ILLEGAL_USER_NAME
}

