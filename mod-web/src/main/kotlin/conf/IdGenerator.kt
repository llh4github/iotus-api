package io.github.llh4github.iotus.conf

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import org.babyfish.jimmer.sql.meta.UserIdGenerator
import org.springframework.stereotype.Component

@Component
class IdGenerator(
    properties: IdUtilProperties
) : UserIdGenerator<Long> {
    init {
        val options = IdGeneratorOptions(properties.workerId)
        options.SeqBitLength = properties.seqBitLength
        YitIdHelper.setIdGenerator(options)
    }

    override fun generate(entityType: Class<*>?): Long {
        return YitIdHelper.nextId()
    }
}