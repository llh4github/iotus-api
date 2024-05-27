package io.github.llh4github.iotus.exception

import io.github.llh4github.iotus.commons.JsonWrapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.error.CodeBasedRuntimeException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExpHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: RuntimeException): JsonWrapper<String> {
        logger.error(e) { "系统出现未知错误: ${e.message}" }
        return JsonWrapper.fail(data = e.message)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): JsonWrapper<String> {
        logger.error(e) { "系统出现未知错误: ${e.message}" }
        return JsonWrapper.fail(data = e.message)
    }

    @ExceptionHandler(value = [CodeBasedRuntimeException::class])
    fun bizException(e: CodeBasedRuntimeException): JsonWrapper<Map<String, Any?>> {
        logger.error(e) { "业务抛出异常: ${e.message}" }
        return JsonWrapper(
            code = e.code,
            module = e.family,
            msg = e.message ?: "",
            data = e.fields
        )
    }
}