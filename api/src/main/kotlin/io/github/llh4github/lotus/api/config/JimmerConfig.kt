package io.github.llh4github.lotus.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.babyfish.jimmer.meta.ImmutableProp
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.spring.cache.RedisCaches
import org.babyfish.jimmer.spring.cache.RedisValueBinder
import org.babyfish.jimmer.sql.cache.Cache
import org.babyfish.jimmer.sql.cache.chain.ChainCacheBuilder
import org.babyfish.jimmer.sql.kt.cache.KCacheFactory
import org.babyfish.jimmer.sql.meta.DatabaseNamingStrategy
import org.babyfish.jimmer.sql.runtime.DefaultDatabaseNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.stereotype.Component
import java.time.Duration

/**
 *
 *
 * Created At 2024/1/13 19:43
 * @author llh
 */
@Component
class JimmerConfig {
    @Bean
    fun databaseNamingStrategy(): DatabaseNamingStrategy =
        DefaultDatabaseNamingStrategy.LOWER_CASE

    @Bean
    fun cacheFactory(
        connectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): KCacheFactory {

        val duration: Duration = Duration.ofDays(10L)
        val redisTemplate = RedisCaches.cacheRedisTemplate(connectionFactory)

        return object : KCacheFactory {


            override fun createObjectCache(type: ImmutableType): Cache<*, *>? =
                ChainCacheBuilder<Any, Any>()
                    .add(
                        MyRedisValueTypeBinder(
                            redisTemplate,
                            objectMapper,
                            type,
                            duration
                        )
                    )
                    .build()

            override fun createAssociatedIdCache(prop: ImmutableProp): Cache<*, *>? =
                createPropCache(prop, duration)

            @SuppressWarnings("all")
            override fun createAssociatedIdListCache(prop: ImmutableProp): Cache<*, List<*>>? =
                createPropCache(prop, duration) as Cache<*, List<*>>?

            override fun createResolverCache(prop: ImmutableProp): Cache<*, *>? =
                createPropCache(prop, duration)

            private fun createPropCache(prop: ImmutableProp, duration: Duration): Cache<*, *>? {
                val binder = MyRedisValueBinder(
                    redisTemplate,
                    objectMapper,
                    prop,
                    duration
                )
                return ChainCacheBuilder<Any, Any>()
                    .add(binder)
                    .build()
            }
        }
    }
}

private const val KEY_PREFIX: String = "jimmer-cache:"

internal class MyRedisValueBinder(
    operations: RedisOperations<String, ByteArray>,
    objectMapper: ObjectMapper,
    prop: ImmutableProp,
    duration: Duration
) : RedisValueBinder<Any, Any>(operations, objectMapper, prop, duration) {

    override fun getKeyPrefix(type: ImmutableType): String {
        return KEY_PREFIX + type.javaClass.getSimpleName() + ':'
    }

    override fun getKeyPrefix(prop: ImmutableProp): String {
        return KEY_PREFIX + prop.declaringType.javaClass.getSimpleName() + '.' + prop.name + ':'
    }
}

internal class MyRedisValueTypeBinder(
    operations: RedisOperations<String, ByteArray>,
    objectMapper: ObjectMapper,
    prop: ImmutableType,
    duration: Duration
) : RedisValueBinder<Any, Any>(operations, objectMapper, prop, duration) {

    override fun getKeyPrefix(type: ImmutableType): String {
        return KEY_PREFIX + type.javaClass.getSimpleName() + ':'
    }

    override fun getKeyPrefix(prop: ImmutableProp): String {
        return KEY_PREFIX + prop.declaringType.javaClass.getSimpleName() + '.' + prop.name + ':'
    }
}
