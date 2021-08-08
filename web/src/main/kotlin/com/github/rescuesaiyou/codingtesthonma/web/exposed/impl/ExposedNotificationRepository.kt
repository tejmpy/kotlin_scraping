package com.github.rescuesaiyou.codingtesthonma.web.exposed.impl

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.Notification
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.NotificationRepository
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.exposed.table.NotificationTable
import com.github.rescuesaiyou.codingtesthonma.web.exposed.table.upsert
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class ExposedNotificationRepository : NotificationRepository {

    override fun save(notification: Notification) {
        NotificationTable.upsert({
            (NotificationTable.userId eq notification.userId.value) and
                (NotificationTable.notificationDate eq notification.notificationDate.value)
        }) {
            it[userId] = notification.userId.value
            it[notificationDate] = notification.notificationDate.value
        }
    }

    override fun findBy(userId: UserId): List<Notification> {
        val query = NotificationTable.select { NotificationTable.userId eq userId.value }
        return query.map(NotificationTable::extract)
    }
}
