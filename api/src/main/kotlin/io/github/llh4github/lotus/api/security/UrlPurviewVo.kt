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
    @Deprecated(message = "不用这个字段啦")
    val purviewCode: String? = null,
) : GrantedAuthority {
    override fun getAuthority(): String {
        return if (purviewCode === null) {
            // 未定义接口权限字符的，返回无法匹配的值
            method.name + url
        } else {
            purviewCode
        }
    }
}
