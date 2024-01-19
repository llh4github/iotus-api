package io.github.llh4github.lotus.api.exceptions

import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.error.CodeBasedRuntimeException
import org.babyfish.jimmer.sql.runtime.ExecutionException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.security.access.AccessDeniedException
/**
 *
 *
 * Created At 2024/1/16 11:46
 * @author llh
 */
@RestControllerAdvice
class ExceptionRestHandler {

    private val logger = KotlinLogging.logger { }

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun accessException(e: AccessDeniedException): JsonWrapper<Nothing> {
        logger.error(e) { "禁止访问： ${e.message}" }
        return JsonWrapper(
            code = "ACCESS_DENIED",
            msg = "用户未登录",
            module = "AUTH"
        )
    }

    @ExceptionHandler(value = [ExecutionException::class])
    fun sqlException(e: ExecutionException): JsonWrapper<Nothing> {
        logger.error(e) { "SQL执行异常： ${e.message}" }
        return JsonWrapper(
            code = "SQL_EXECUTION_ERROR",
            msg = "SQL执行异常",
            module = "DB"
        )
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun runtimeE(e: RuntimeException): JsonWrapper<Nothing> {
        logger.error(e) { "应用运行异常: ${e.message}" }
        return JsonWrapper(
            code = "UNKNOWN",
            msg = "未知异常",
            module = "SYSTEM"
        )
    }

    @ExceptionHandler(value = [CodeBasedRuntimeException::class])
    fun bizException(e: CodeBasedRuntimeException): JsonWrapper<Map<String, Any?>> {
        logger.debug(e) { "业务抛出异常: ${e.message}" }
        return JsonWrapper(
            code = e.code,
            module = e.family,
            msg = e.message ?: "",
            data = e.fields
        )
    }
}