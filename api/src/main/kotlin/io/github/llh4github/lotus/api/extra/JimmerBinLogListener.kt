package io.github.llh4github.lotus.api.extra

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.babyfish.jimmer.sql.event.binlog.BinLog
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Component

/**
 *
 *
 * Created At 2024/1/17 14:17
 * @author llh
 */
@Component
class JimmerBinLogListener(
    private val sqlClient: KSqlClient,
    private val container: RedisMessageListenerContainer
) {
    companion object {
        @JvmStatic
        private val MAPPER = ObjectMapper()

        private val logger = KotlinLogging.logger { }
    }


    @PostConstruct
    fun registerListenerAdapter() {
        container.addMessageListener(Receiver(sqlClient.binLog), PatternTopic("db-change-notify:maxwell"))
    }

    internal class Receiver(private val binLog: BinLog) : MessageListener {
        override fun onMessage(message: Message, pattern: ByteArray?) {
            handleDbChange(String(message.body))
        }

        private fun handleDbChange(json: String) {
            val node = MAPPER.readTree(json)
            val tableName = node["table"].asText()
            val type = node["type"].asText()
            val data = node["data"]
            logger.debug { "binlog 收到新数据： $data" }
            when (type) {
                "insert" ->
                    binLog.accept(tableName, null, data)

                "update" ->
                    binLog.accept(tableName, node["old"], data)

                "delete" ->
                    binLog.accept(tableName, data, null)
            }

        }
    }
}