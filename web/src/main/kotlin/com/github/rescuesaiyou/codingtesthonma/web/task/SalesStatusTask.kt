package com.github.rescuesaiyou.codingtesthonma.web.task

import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.SalesStatus
import com.github.rescuesaiyou.codingtesthonma.web.domain.model.salesstatus.Status
import com.github.rescuesaiyou.codingtesthonma.web.service.salesstatus.SalesStatusService
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.YearMonth

@Component
class SalesStatusTask(
    @Value("\${tdr.url}") private val tdrUrl: String,
    private val salesStatusService: SalesStatusService
) {
    private val url = "$tdrUrl/ticket/sales_status"
    private val calendarCellSelector = ".calendarTable tbody > tr > td:not(.pass)"

    @Scheduled(cron = "0 2/5 * * * *")
    fun save() {
        // 販売状況を取得可能な年月の生成
        val yearMonths = (1..SalesStatus.monthLength).map {
            YearMonth.now().plusMonths((it - 1).toLong())
        }

        // 年月ごとにページ取得
        for (yearMonth in yearMonths) {
            // 対象ページへ接続しページ要素を取得
            val doc = Jsoup.connect(
                "$url/${yearMonth.year}${yearMonth.monthValue.toString().padStart(2, '0')}"
            ).get()

            // 日付ごとの要素を取得
            val elements = doc.select(calendarCellSelector)

            val salesStatuses = elements.mapNotNull { element ->
                buildSalesStatusFrom(element)
            }.filter { it.date <= yearMonth.atEndOfMonth() } // 取得したページの年月以降の日付は除外する
            if (salesStatuses.isNotEmpty()) salesStatusService.save(salesStatuses)
        }
    }

    private fun buildSalesStatusFrom(element: Element): SalesStatus? {
        val date = element.select(SalesStatus.dateSelector.selectorWithBrackets()).firstOrNull()?.let {
            SalesStatus.dateFrom(it.attr(SalesStatus.dateSelector.selector()))
        }
        return date?.let {
            SalesStatus(
                date = date,
                tdlStatus = Status.valueFrom(element.select(SalesStatus.TDL_STATUS_SELECTOR).first()!!),
                tdsStatus = Status.valueFrom(element.select(SalesStatus.TDS_STATUS_SELECTOR).first()!!)
            )
        }
    }
}
