package com.github.rescuesaiyou.codingtesthonma.web.service.salesstatus

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatus
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatusRepository
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.service.notification.NotificationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SalesStatusService(
    private val notificationService: NotificationService,
    private val salesStatusRepository: SalesStatusRepository
) {

    @Transactional(readOnly = true)
    fun getBy(userId: UserId): List<SalesStatus> {
        // ユーザーが販売状況を確認したい日付を取得
        val notifications = notificationService.getBy(userId)
        val targetDates = notifications.map { it.notificationDate.value }

        return salesStatusRepository.findBy(targetDates)
    }

    @Transactional(readOnly = true)
    fun getList(): List<SalesStatus> {
        return salesStatusRepository.findAll()
    }

    @Transactional(readOnly = false)
    fun save(salesStatuses: List<SalesStatus>): List<SalesStatus> {
        salesStatusRepository.save(salesStatuses)
        return salesStatuses
    }
}
