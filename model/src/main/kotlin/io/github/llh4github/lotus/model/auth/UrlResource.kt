package io.github.llh4github.lotus.model.auth

import io.github.llh4github.lotus.model.BaseModel
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

    @Key
    val path: String

    @get:Schema(title = "HTTP方法")
    val method: HttpMethodEnums


    @OneToOne
    @JoinColumn(name = "purview_code_id")
    @OnDissociate(value = DissociateAction.SET_NULL)
    val purviewCode: PurviewCode?
}