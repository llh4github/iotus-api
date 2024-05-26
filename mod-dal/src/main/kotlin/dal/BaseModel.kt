package io.github.llh4github.iotus.dal

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
interface BaseModel {
    @Id
    @GeneratedValue(generatorRef = "idGenerator")
    @get:Schema(title = "数据ID", example = "114514")
    val id: Long

    @get:Schema(title = "数据创建时间", example = "2022-11-11 01:01:01")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime

    @get:Schema(title = "数据更新时间", example = "2022-01-01 00:00:00")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime
}