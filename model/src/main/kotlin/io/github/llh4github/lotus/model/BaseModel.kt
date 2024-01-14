import com.fasterxml.jackson.annotation.JsonFormat
import io.github.llh4github.lotus.model.auth.User
import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.sql.*
import java.time.LocalDateTime

/**
 *
 */
@MappedSuperclass
interface BaseModel {

    @Id
    @GeneratedValue(generatorRef = "idUtil")
    @Column(name = "id")
    val id: Long

    @get:Schema(title = "创建时间")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdTime: LocalDateTime?

    @get:Schema(title = "更新时间")
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedTime: LocalDateTime?

    // created_by_user_id
    @IdView(value = "createdByUser")
    val createdBy: Long?


    // updated_by_user_id
    @IdView(value = "updatedByUser")
    val updatedBy: Long?

    @ManyToOne
    @OnDissociate(DissociateAction.SET_NULL)
    val createdByUser: User?

    @ManyToOne
    @OnDissociate(DissociateAction.SET_NULL)
    val updatedByUser: User?
}
