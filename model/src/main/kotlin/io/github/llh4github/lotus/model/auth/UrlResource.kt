package io.github.llh4github.lotus.model.auth

import BaseModel
import io.github.llh4github.lotus.model.HttpMethodEnums
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.*

/**
 *
 *
 * Created At 2024/1/14 10:36
 * @author llh
 */
@Entity
@Table(name = "auth_url_resource")
interface UrlResource : BaseModel {
    @Id
    @GeneratedValue(generatorRef = "idUtil")
    @Column(name = "id")
    val urlResourceId: Long

    @Key
    val path: String

    @get:Schema(title = "HTTP方法")
    val method: HttpMethodEnums

    @ManyToMany(mappedBy = "urlResources")
    val roles: List<Role>

}