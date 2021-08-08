package com.github.rescuesaiyou.codingtesthonma.web.exposed.table

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.notification.Notification
import com.github.rescuesaiyou.codingtesthonma.web.exposed.getAs
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object NotificationTable : Table("notification") {
    val userId = uuid("user_id").references(UserTable.id)

    val notificationDate = date("notification_date")

    override val primaryKey = PrimaryKey(userId)

    fun extract(row: ResultRow): Notification =
        Notification(
            userId = row.getAs(userId),
            notificationDate = row.getAs(notificationDate)
        )
}
