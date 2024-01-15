package io.github.llh4github.lotus.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 *
 *
 * Created At 2024/1/15 10:10
 * @author llh
 */
@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        val serializer = StringRedisSerializer()
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer()

        redisTemplate.keySerializer = serializer
        redisTemplate.valueSerializer = genericJackson2JsonRedisSerializer

        redisTemplate.hashKeySerializer = serializer
        redisTemplate.hashValueSerializer = genericJackson2JsonRedisSerializer
        // 事务
//        redisTemplate.setEnableTransactionSupport(true)
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }
}