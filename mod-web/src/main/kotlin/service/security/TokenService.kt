package io.github.llh4github.iotus.service.security

import io.github.llh4github.iotus.conf.IdGenerator
import io.github.llh4github.iotus.conf.SecurityProperties
import io.github.llh4github.iotus.security.UserAuthDetails
import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class TokenService(
    private val idGenerator: IdGenerator,
    private val redisTemplate: StringRedisTemplate,
    private val securityProperties: SecurityProperties,
) {
    private val logger = KotlinLogging.logger {}
    val tokenProperties by lazy { securityProperties.token }
    private val parser by lazy {
        Jwts.parser().verifyWith(tokenProperties.secretKey).build()
    }
    private val BAN_TOKEN_CACHE_KEY = "token:ban:"
    private val USERNAME_KEY = "USERNAME"
    private val USER_ID_KEY = "USER_ID"

    fun banToken(token: String) {
        val key = "${BAN_TOKEN_CACHE_KEY}$token"
        redisTemplate.opsForValue().set(key, "BANNED", securityProperties.token.maxExpireTimeMs, TimeUnit.MILLISECONDS)
    }
    private fun tokenClaims(details: UserAuthDetails): Map<String, Any> {
        return mapOf<String, Any>(
            USERNAME_KEY to details.username,
            USER_ID_KEY to details.userId
        )
    }

    fun createAccessToken(details: UserAuthDetails): String {
        return createAccessToken(tokenClaims(details))
    }

    fun createRefreshToken(details: UserAuthDetails): String {
        return createRefreshToken(tokenClaims(details))
    }
    fun createAccessToken(map: Map<String, Any>): String {
        val expire = System.currentTimeMillis() + tokenProperties.expireTimeMs
        val builder = tokenBuilder(map)
            .expiration(Date(expire))
        builder.header().add("typ", "access")
        return builder.compact()
    }

    fun createRefreshToken(map: Map<String, Any>): String {
        val expire = System.currentTimeMillis() + tokenProperties.refreshExpireTimeMs
        val builder = tokenBuilder(map)
            .expiration(Date(expire))
        builder.header().add("typ", "refresh")
        return builder.compact()
    }

    private fun tokenBuilder(map: Map<String, Any>): JwtBuilder {
        return Jwts.builder()
            .claims(map)
            .id(idGenerator.nextIdStr())
            .signWith(tokenProperties.secretKey)
    }

    //#region 验证token

    private fun isNotInBan(token: String): Boolean {
        val key = "${BAN_TOKEN_CACHE_KEY}$token"
        val value = redisTemplate.hasKey(key)
        return !value
    }

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
    //#endregion
}