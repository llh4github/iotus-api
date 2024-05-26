package io.github.llh4github.iotus.dal

import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
interface BaseModel {
    @Id
    @GeneratedValue(generatorRef = "idGenerator")
    val id: Long
    val createTime: LocalDateTime
    val updateTime: LocalDateTime
}