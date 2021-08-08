package com.github.rescuesaiyou.codingtesthonma.web.api.controller.notification

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.Notification
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.NotificationDate
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import java.time.LocalDate

object ApiInfo {

    data class GetList(
        val users: List<Notification>
    )

    object NotificationDetail {
        data class ResponseBody(
            val userId: UserId,
            val date: NotificationDate
        )

        data class RequestBody(
            val userId: UserId,
            val date: LocalDate
        )
    }
}
