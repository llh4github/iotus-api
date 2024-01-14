package io.github.llh4github.lotus.api.config

import BaseModel
import BaseModelDraft
import io.github.oshai.kotlinlogging.KotlinLogging
import org.babyfish.jimmer.kt.get
import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftInterceptor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 *
 *
 * Created At 2024/1/13 20:03
 * @author llh
 */
@Component
class BaseModelDraftInterceptor : DraftInterceptor<BaseModel, BaseModelDraft> {
    private val logger = KotlinLogging.logger {}
    private fun currentUserId(): Long {
        return 1
    }

    override fun beforeSave(draft: BaseModelDraft, original: BaseModel?) {
        val now = LocalDateTime.now()
        if (!isLoaded(draft, BaseModel::updatedTime)) {
            draft.updatedTime = now
        }


        if (original === null) {
            logger.debug { "get createdTime : ${get(draft, BaseModel::createdTime)}" }
            if (!isLoaded(draft, BaseModel::createdTime)) {
                draft.createdTime = now
            }
            if (!isLoaded(draft, BaseModel::createdByUser)) {
                draft.createdBy = currentUserId()
            }
        }
    }

}