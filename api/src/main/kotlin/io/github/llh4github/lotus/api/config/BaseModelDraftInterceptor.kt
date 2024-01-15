package io.github.llh4github.lotus.api.config

import BaseModel
import BaseModelDraft
import io.github.llh4github.lotus.api.utils.SecurityUtil
import io.github.oshai.kotlinlogging.KotlinLogging
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
        return SecurityUtil.currentUserId()
    }

    override fun beforeSave(draft: BaseModelDraft, original: BaseModel?) {
        val now = LocalDateTime.now()
        if (!isLoaded(draft, BaseModel::updatedTime)) {
            draft.updatedTime = now
        }

        if (!isLoaded(draft, BaseModel::updatedByUser)) {
            draft.updatedBy = currentUserId()
        }

        if (original === null) {
            if (!isLoaded(draft, BaseModel::createdTime)) {
                draft.createdTime = now
            }
            if (!isLoaded(draft, BaseModel::createdByUser)) {
                draft.createdBy = currentUserId()
            }
        }
    }

}