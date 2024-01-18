package io.github.llh4github.lotus.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.llh4github.lotus.commons.JsonWrapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import java.nio.charset.StandardCharsets

/**
 *
 *
 * Created At 2024/1/12 14:40
 * @author llh
 */
object ServletUtil {
    private val logger = KotlinLogging.logger {}
    private val objectMapper by lazy { ObjectMapper() }
    fun writeJson(response: HttpServletResponse, data: JsonWrapper<out Any?>) {
        val json = objectMapper.writeValueAsString(data)
        response.status = HttpStatus.OK.value()
        response.contentType = "application/json"
        response.characterEncoding = StandardCharsets.UTF_8.name()
        try {
            response.writer.write(json)
        } catch (e: Exception) {
            logger.error(e) { "向响应中写入json出错 $json" }
        }
    }
}