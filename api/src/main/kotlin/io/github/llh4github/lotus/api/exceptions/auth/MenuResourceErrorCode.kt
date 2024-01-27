package io.github.llh4github.lotus.api.exceptions.auth

import org.babyfish.jimmer.error.ErrorFamily
import org.babyfish.jimmer.error.ErrorField

/**
 *
 *
 * Created At 2024/1/23 18:46
 * @author llh
 */
@ErrorFamily
enum class MenuResourceErrorCode {

    PATH_DUPLICATE,

    @ErrorField(name = "existPurviewCode", type = String::class, list = true)
    PURVIEW_CODE_DUPLICATE,
}