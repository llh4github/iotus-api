package io.github.llh4github.lotus.api.exceptions

import io.github.llh4github.lotus.commons.AppErrorEnums
import io.github.llh4github.lotus.commons.JsonWrapper
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 *
 *
 * Created At 2024/1/16 11:46
 * @author llh
 */
@RestControllerAdvice
class ExceptionRestHandler {

    @ExceptionHandler(value = [RuntimeException::class])
    fun runtimeE(e: RuntimeException): JsonWrapper<Nothing> {
        return JsonWrapper.response(AppErrorEnums.Error, null)
    }
}