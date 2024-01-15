package io.github.llh4github.lotus.api.config.properties

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

/**
 *
 *
 * Created At 2024/1/12 15:38
 * @author llh
 */

@Configuration
@ConfigurationProperties(prefix = "lotus.security")
data class SecurityProperties(
    /**
     * 允许匿名访问的url列表
     */
    var annoUrl: List<String> = emptyList(),

    /**
     * jwt生成配置
     */
    var token: TokenProperties = TokenProperties()

)

data class TokenProperties(
    /**
     * 签发人。通常是访问域名。
     */
    var issuer: String = "lotus-api",
    /**
     * 令牌秘钥
     *
     * 用base64，至少需要43个字符，不含特殊符号。
     */
    var secret: String = "VyHZ8YGV9w94dRw8ixVzJgcoDXqvRGrFOHzCxiMIgbgmM",
    /**
     * 时间单位
     */
    var timeUnit: TimeUnit = TimeUnit.DAYS,
    /**
     * 令牌有效期
     */
    var expireTime: Int = 3,
    /**
     * refresh令牌有效期
     */
    var refreshExpireTime: Int = 4,
) {
    /**
     * 令牌有效时间(毫秒)
     */
    val expireTimeMs by lazy {
        TimeUnit.MILLISECONDS.convert(expireTime.toLong(), timeUnit)
    }

    /**
     * refresh令牌有效时间(毫秒)
     */
    val refreshExpireTimeMs by lazy {
        TimeUnit.MILLISECONDS.convert(refreshExpireTime.toLong(), timeUnit)
    }

    /**
     * 生成密钥
     */
    val secretKey: SecretKey by lazy {
        val bytes = Decoders.BASE64.decode(secret)
        Keys.hmacShaKeyFor(bytes)
    }

    /**
     * 令牌最大有效时间(毫秒)
     */
    val maxExpireTimeMs: Long by lazy {
        if (refreshExpireTimeMs > expireTimeMs)
            refreshExpireTimeMs
        else expireTimeMs
    }
}


