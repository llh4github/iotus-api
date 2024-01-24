package io.github.llh4github.lotus.model.auth

import io.github.llh4github.lotus.model.BaseModel
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.JoinColumn
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.Table

/**
 *
 *
 * Created At 2024/1/24 11:08
 * @author llh
 */
@Entity
@Table(name = "auth_purview_code")
interface PurviewCode : BaseModel {

    @Key
    @get:Schema(title = "权限代号")
    val code: String

    @get:Schema(title = "说明")
    val remark: String

    @ManyToOne
    @JoinColumn(name = "menu_resource_id")
    val menu: MenuResource

}