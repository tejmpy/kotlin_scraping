package com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import javax.validation.Valid

class Notification(
    @field: Valid val userId: UserId,
    @field: Valid val notificationDate: NotificationDate
)
