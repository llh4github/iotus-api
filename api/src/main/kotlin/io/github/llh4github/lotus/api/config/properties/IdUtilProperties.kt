package io.github.llh4github.lotus.api.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 *
 *
 * Created At 2024/1/12 18:07
 * @author llh
 */

@Configuration
@ConfigurationProperties(prefix = "lotus.id")
data class IdUtilProperties(
    /**
     *  WorkerId 最大值为2^6-1，即默认最多支持64个节点。
     *
     *  **多实例部署时最好修改为不同值。**
     */
    val workerId: Short = 1,
    /**
     * **一般不用改这个配置。**
     *
     * 默认值6，限制每毫秒生成的ID个数。若生成速度超过5万个/秒，建议加大 SeqBitLength 到 10。
     */
    val seqBitLength: Byte = 6,
)