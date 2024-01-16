package io.github.llh4github.lotus.api.api

import io.github.llh4github.lotus.commons.JsonWrapper

/**
 * 接口类的基类，包含一些通用方法
 *
 * Created At 2023/12/30 21:20
 * @author llh
 */
abstract class BaseApi {
    fun <T> ok(
        data: T? = null,
    ): JsonWrapper<T> {
        return JsonWrapper.ok(msg = "", data = data)
    }

}