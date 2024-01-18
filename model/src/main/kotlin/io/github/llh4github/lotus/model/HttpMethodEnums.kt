package io.github.llh4github.lotus.model

import org.babyfish.jimmer.sql.EnumItem
import org.babyfish.jimmer.sql.EnumType

/**
 *http method枚举。0：all,1:get,2:post,3:delete,
 *
 * Created At 2024/1/14 10:30
 * @author llh
 */
@EnumType(EnumType.Strategy.ORDINAL)
enum class HttpMethodEnums {
    @EnumItem(ordinal = 0)
    ALL,

    @EnumItem(ordinal = 120)
    NONE,

    @EnumItem(ordinal = 1)
    GET,

    @EnumItem(ordinal = 2)
    POST,

    @EnumItem(ordinal = 3)
    DELETE,

    @EnumItem(ordinal = 4)
    PUT,
    ;


}

fun convertMethodEnums(method: String): HttpMethodEnums {
    for (it in HttpMethodEnums.entries) {
        if (it.name == method.uppercase()) {
            return it
        }
    }
    return HttpMethodEnums.NONE
}