package io.github.llh4github.lotus.api.security

import io.github.llh4github.lotus.model.HttpMethodEnums
import org.springframework.security.core.GrantedAuthority

/**
 * 权限信息
 *
 * Created At 2024/1/15 22:08
 * @author llh
 */
data class PurviewInfo(
    val code: String,
    val type: Type,
    /** for URL type */
    val methodEnums: HttpMethodEnums? = null,
) : GrantedAuthority {
    override fun getAuthority(): String {
        return code
    }

    /**
     * 权限字符类型
     */
    enum class Type {
        URL, MENU, ROLE
    }

    companion object {
        @JvmStatic
        fun url(code: String, methodEnums: HttpMethodEnums): PurviewInfo {
            return PurviewInfo(code, Type.URL, methodEnums)
        }

        @JvmStatic
        fun menu(code: String): PurviewInfo {
            return PurviewInfo(code, Type.MENU)
        }

        @JvmStatic
        fun role(code: String): PurviewInfo {
            return PurviewInfo(code, Type.ROLE)
        }
    }
}


