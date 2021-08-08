package com.github.rescuesaiyou.codingtesthonma.web.service.notification

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.Notification
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.NotificationRepository
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository
) {

    @Transactional(readOnly = false)
    fun save(notification: Notification): Notification {
        notificationRepository.save(notification)
        return notification
    }

    @Transactional(readOnly = true)
    fun getBy(userId: UserId): List<Notification> {
        return notificationRepository.findBy(userId)
    }
}
