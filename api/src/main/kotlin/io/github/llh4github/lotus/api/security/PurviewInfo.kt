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
    @Deprecated(message = "改用权限字符了")
    val methodEnums: HttpMethodEnums? = null,
) : GrantedAuthority {
    override fun getAuthority(): String {
        return code
    }

    /**
     * 权限字符类型
     */
    enum class Type {
        URL, MENU, ROLE, PURVIEW_CODE
    }

    companion object {
        private const val ROLE_PREFIX = "ROLE_"

        @Deprecated(
            message = "考虑移除",
            replaceWith = ReplaceWith(
                "PurviewInfo(code, Type.PURVIEW_CODE)",
                "io.github.llh4github.lotus.api.security.PurviewInfo",
                "io.github.llh4github.lotus.api.security.PurviewInfo.Type"
            )
        )
        @JvmStatic
        fun url(code: String, methodEnums: HttpMethodEnums): PurviewInfo {
            return PurviewInfo(code, Type.URL, methodEnums)
        }

        @JvmStatic
        fun purviewCode(code: String): PurviewInfo {
            return PurviewInfo(code, Type.PURVIEW_CODE)
        }

        @JvmStatic
        fun menu(code: String): PurviewInfo {
            return PurviewInfo(code, Type.MENU)
        }

        @JvmStatic
        fun role(code: String): PurviewInfo {
            return PurviewInfo(ROLE_PREFIX + code, Type.ROLE)
        }
    }
}


