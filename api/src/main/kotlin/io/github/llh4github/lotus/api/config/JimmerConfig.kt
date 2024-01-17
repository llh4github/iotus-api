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

        val redisTemplate = RedisCaches.cacheRedisTemplate(connectionFactory)

        return object : KCacheFactory {

            override fun createObjectCache(type: ImmutableType): Cache<*, *>? =
                ChainCacheBuilder<Any, Any>()
                    .add(
                        RedisValueBinder(
                            redisTemplate,
                            objectMapper,
                            type,
                            Duration.ofMinutes(30)
                        )
                    )
                    .build()

            override fun createAssociatedIdCache(prop: ImmutableProp): Cache<*, *>? =
                createPropCache(prop, Duration.ofMinutes(10))

            @SuppressWarnings("all")
            override fun createAssociatedIdListCache(prop: ImmutableProp): Cache<*, List<*>>? =
                createPropCache(prop, Duration.ofMinutes(5)) as Cache<*, List<*>>?

            override fun createResolverCache(prop: ImmutableProp): Cache<*, *>? =
                createPropCache(prop, Duration.ofMinutes(5))

            private fun createPropCache(prop: ImmutableProp, duration: Duration): Cache<*, *>? =
                ChainCacheBuilder<Any, Any>()
                    .add(
                        RedisValueBinder(
                            redisTemplate,
                            objectMapper,
                            prop,
                            duration
                        )
                    )
                    .build()
        }
    }
}