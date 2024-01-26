package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.model.HttpMethodEnums
import org.springframework.security.core.GrantedAuthority

/**
 *
 *
 * Created At 2024/1/24 17:24
 * @author llh
 */
data class UrlPurviewVo(
    val url: String,
    val method: HttpMethodEnums,

) : GrantedAuthority {
    override fun getAuthority(): String {
        // 自定义的验证逻辑，这里的返回值不重要
        return method.name + url
    }
}
