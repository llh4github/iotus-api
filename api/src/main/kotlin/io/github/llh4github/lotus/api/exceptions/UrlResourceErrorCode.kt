package io.github.llh4github.lotus.api.exceptions

import io.github.llh4github.lotus.model.HttpMethodEnums
import org.babyfish.jimmer.error.ErrorFamily
import org.babyfish.jimmer.error.ErrorField

/**
 *
 *
 * Created At 2024/1/16 17:01
 * @author llh
 */
@ErrorFamily
enum class UrlResourceErrorCode {
    @ErrorField(name = "path", type = String::class)
    @ErrorField(name = "method", type = HttpMethodEnums::class)
    PATH_DUPLICATE,
}