package io.github.llh4github.lotus.api.utils

import io.github.llh4github.lotus.api.config.TokenProperties
import io.jsonwebtoken.Jwts
import java.util.*

/**
 *
 *
 * Created At 2024/1/12 17:56
 * @author llh
 */
internal object TokenUtil {
    fun createToken(map: Map<String, Any>, properties: TokenProperties): String {
        val builder = Jwts.builder()
            .claims(map)
            .issuer(properties.issuer)
            .signWith(properties.secretKey)
            .expiration(Date(properties.expireTimeMs))
        return builder.compact()
    }


}