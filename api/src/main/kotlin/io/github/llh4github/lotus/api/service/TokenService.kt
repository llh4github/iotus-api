package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.api.config.properties.SecurityProperties
import io.github.llh4github.lotus.api.dto.UserAuthDetails
import io.github.llh4github.lotus.api.utils.IdUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *
 *
 * Created At 2024/1/12 18:22
 * @author llh
 */
@Service
class TokenService(
    private val idUtil: IdUtil,
    private val securityProperties: SecurityProperties,
    private val redisTemplate: StringRedisTemplate,
) {

    val tokenProperties by lazy { securityProperties.token }
    private val logger = KotlinLogging.logger {}
    private val parser by lazy {
        Jwts.parser().verifyWith(tokenProperties.secretKey).build()
    }
    private val USERNAME_KEY = "USERNAME"
    private val USER_ID_KEY = "USER_ID"
    private val BAN_TOKEN_CACHE_KEY = "token:ban"

    //region create token
    private fun tokenClaims(details: UserAuthDetails): Map<String, Any> {
        return mapOf<String, Any>(
            USERNAME_KEY to details.username,
            USER_ID_KEY to details.userId
        )
    }

    fun createToken(details: UserAuthDetails): String {
        return createToken(tokenClaims(details))
    }

    fun createRefreshToken(details: UserAuthDetails): String {
        return createRefreshToken(tokenClaims(details))
    }

    private fun createRefreshToken(map: Map<String, Any>): String {
        val expire = System.currentTimeMillis() + tokenProperties.refreshExpireTimeMs
        val builder = tokenBuilder(map)
            .expiration(Date(expire))
        builder.header().add("typ", "refresh")
        return builder.compact()
    }

    private fun createToken(map: Map<String, Any>): String {
        val expire = System.currentTimeMillis() + tokenProperties.expireTimeMs
        val builder = tokenBuilder(map)
            .expiration(Date(expire))
        builder.header().add("typ", "access")
        return builder.compact()
    }

    private fun tokenBuilder(map: Map<String, Any>): JwtBuilder {
        return Jwts.builder()
            .claims(map)
            .id(idUtil.nextIdStr())
            .signWith(tokenProperties.secretKey)
    }

    //endregion create token

    //region parse token
    fun isInvalid(token: String): Boolean {
        return !verify(token)
    }

    fun verify(token: String): Boolean {
        try {
            parser.parseSignedClaims(token)
            return isNotInBan(token)
        } catch (e: Exception) {
            logger.debug(e) { "token验证不通过： $token" }
            return false
        }
    }

    private fun isNotInBan(token: String): Boolean {
        val key = "${BAN_TOKEN_CACHE_KEY}:$token"
        val value = redisTemplate.opsForValue().get(key)
        return value == null
    }

    fun parserUsername(token: String): String {
        val claims = parser.parseSignedClaims(token).payload
        return claims.getOrDefault(USERNAME_KEY, "") as String
    }

    fun parserUserId(token: String): Long? {
        val claims = parser.parseSignedClaims(token).payload
        return claims.get(USER_ID_KEY, Long::class.java)
    }

    fun parserTokenId(token: String): String {
        val claims = parser.parseSignedClaims(token).payload
        return claims.id
    }
    //endregion parse token

    fun banToken(token: String) {
        val key = "${BAN_TOKEN_CACHE_KEY}:$token"
        redisTemplate.opsForValue().set(key, "BANNED", securityProperties.token.maxExpireTimeMs, TimeUnit.MILLISECONDS)
    }
}