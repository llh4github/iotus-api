package io.github.llh4github.lotus.api.service

import io.github.llh4github.lotus.api.config.TokenProperties
import io.github.llh4github.lotus.api.utils.IdUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.util.*

/**
 *
 *
 * Created At 2024/1/12 18:22
 * @author llh
 */
@Service
class TokenService(
    private val idUtil: IdUtil,
    private val properties: TokenProperties,
) {

    private val logger = KotlinLogging.logger {}
    private val parser by lazy {
        Jwts.parser().verifyWith(properties.secretKey).build()
    }

    //region create token
    private fun createRefreshToken(map: Map<String, Any>): String {
        val builder = tokenBuilder(map)
            .expiration(Date(properties.refreshExpireTimeMs))
        builder.header().add("typ", "refresh")
        return builder.compact()
    }

    private fun createToken(map: Map<String, Any>): String {
        val builder = tokenBuilder(map)
            .expiration(Date(properties.expireTimeMs))
        builder.header().add("typ", "access")
        return builder.compact()
    }

    private fun tokenBuilder(map: Map<String, Any>): JwtBuilder {
        return Jwts.builder()
            .claims(map)
            .id(idUtil.nextIdStr())
            .signWith(properties.secretKey)
    }

    //endregion create token

    //region parse token
    fun verify(token: String): Boolean {
        try {
            parser.parse(token)
            return true
        } catch (e: Exception) {
            logger.debug(e) { "token验证不通过： $token" }
            return false
        }
    }

    fun parserUsername(token: String): String? {
        val claims = parser.parseSignedClaims(token).payload
//        claims
        return null
    }

    //endregion parse token
}