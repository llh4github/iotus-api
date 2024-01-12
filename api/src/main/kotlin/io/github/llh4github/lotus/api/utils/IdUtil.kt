package io.github.llh4github.lotus.api.utils

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import io.github.llh4github.lotus.api.config.IdUtilProperties
import org.springframework.stereotype.Component

/**
 *
 *
 * Created At 2024/1/12 18:07
 * @author llh
 */
@Component
class IdUtil(properties: IdUtilProperties) {

    init {
        val option = IdGeneratorOptions()
        option.WorkerId = properties.workerId
        option.SeqBitLength = properties.seqBitLength
        YitIdHelper.setIdGenerator(option)
    }

    fun nextId(): Long {
        return YitIdHelper.nextId()
    }

    fun nextIdStr(): String {
        return "${YitIdHelper.nextId()}"
    }
}