package io.github.llh4github.lotus.api.config

import org.babyfish.jimmer.sql.meta.DatabaseNamingStrategy
import org.babyfish.jimmer.sql.runtime.DefaultDatabaseNamingStrategy
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

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
}