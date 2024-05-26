package io.github.llh4github.iotus.conf

import org.babyfish.jimmer.sql.meta.DatabaseNamingStrategy
import org.babyfish.jimmer.sql.runtime.DefaultDatabaseNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class JimmerConfig {
    @Bean
    fun databaseNamingStrategy(): DatabaseNamingStrategy =
        DefaultDatabaseNamingStrategy.LOWER_CASE
}