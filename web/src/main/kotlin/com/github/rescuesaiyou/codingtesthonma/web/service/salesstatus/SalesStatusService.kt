package com.github.rescuesaiyou.codingtesthonma.web.service.salesstatus

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatus
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.Status
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.user.UserId
import com.github.rescuesaiyou.codingtesthonma.web.service.notification.NotificationService
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.YearMonth

@Service
class SalesStatusService(
    @Value("\${tdr.url}") private val tdrUrl: String,
    private val notificationService: NotificationService
) {

    @Transactional(readOnly = true)
    fun getBy(userId: UserId): List<SalesStatus> {
        val url = "$tdrUrl/ticket/sales_status"
        val now = YearMonth.now()

        // 取得できる月は当月含めて3ヶ月
        val yearMonths = listOf(
            now,
            now.plusMonths(1L),
            now.plusMonths(2L)
        )

        // ユーザーが販売状況を確認したい日付を取得
        val notifications = notificationService.getBy(userId)
        val targetDates = notifications.map { it.notificationDate.value }

        val salesStatuses = mutableListOf<SalesStatus>()
        for (yearMonth in yearMonths) {
            if (!targetDates.map { it.year to it.month }.contains(yearMonth.year to yearMonth.month)) continue

            val doc =
                Jsoup.connect("$url/${yearMonth.year}${yearMonth.monthValue.toString().padStart(2, '0')}").get()
            val elements = doc.select(".calendarTable tbody > tr > td:not(.pass)")

            for (element in elements) {
                try {
                    val salesStatus = buildSalesStatus(
                        element = element,
                        yearMonth = yearMonth,
                        targetDates = targetDates
                    )
                    salesStatus?.apply {
                        salesStatuses.add(this)
                    }
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
        return salesStatuses
    }

    private fun buildSalesStatus(element: Element, yearMonth: YearMonth, targetDates: List<LocalDate>): SalesStatus? {
        val date = SalesStatus.dateFrom(yearMonth, element.select(".day").text())
        if (!targetDates.contains(date)) return null

        return date?.let {
            SalesStatus(
                date = date,
                tdlStatus = Status.valueFrom(element.select(".tdl").first()!!),
                tdsStatus = Status.valueFrom(element.select(".tds").first()!!)
            )
        }
    }
}
