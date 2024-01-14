package io.github.llh4github.lotus.api.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 *
 *
 * Created At 2024/1/14 10:47
 * @author llh
 */
@Configuration
@ConfigurationProperties(prefix = "lotus")
data class AppCommonProperties(
    /**
     * 服务显示名称
     */
    var title: String = "lotus",

    )
