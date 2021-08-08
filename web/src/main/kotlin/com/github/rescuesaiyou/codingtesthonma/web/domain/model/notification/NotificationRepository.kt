package com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId

interface NotificationRepository {
    fun save(notification: Notification)

    fun findBy(userId: UserId): List<Notification>
}
