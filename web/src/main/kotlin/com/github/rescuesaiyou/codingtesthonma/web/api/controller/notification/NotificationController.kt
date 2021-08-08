package com.github.rescuesaiyou.codingtesthonma.web.api.controller.notification

import com.github.rescuesaiyou.codingtesthonma.web.api.controller.ApiPath
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.Notification
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.NotificationDate
import com.github.rescuesaiyou.codingtesthonma.web.service.notification.NotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class NotificationController(
    private val notificationService: NotificationService
) {

    @PostMapping(ApiPath.Notification.save)
    fun register(
        @RequestBody @Valid body: ApiInfo.NotificationDetail.RequestBody
    ): ResponseEntity<ApiInfo.NotificationDetail.ResponseBody> {
        val notification = notificationService.save(
            Notification(
                userId = body.userId,
                notificationDate = NotificationDate(body.date)
            )
        )

        return ResponseEntity.ok().body(
            ApiInfo.NotificationDetail.ResponseBody(
                userId = notification.userId,
                date = notification.notificationDate
            )
        )
    }
}
